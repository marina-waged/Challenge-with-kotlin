package com.example.elmenuschallenge.view.menusList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elmenuschallenge.ElMenusChallengeApplication
import com.example.elmenuschallenge.model.ItemData
import com.example.elmenuschallenge.model.TagData
import com.example.elmenuschallenge.view.menusDetails.DetailsActivity
import com.example.elmenuschallenge.view.menusList.adapter.ItemListener
import com.example.elmenuschallenge.view.menusList.adapter.ItemsListAdapter
import com.example.elmenuschallenge.view.menusList.adapter.TagsListAdapter
import com.example.elmenuschallenge.view.menusList.adapter.TagsListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.menus_list_activity.*
import java.lang.ref.WeakReference
import javax.inject.Inject
import androidx.core.view.ViewCompat
import androidx.core.app.ActivityOptionsCompat
import android.widget.ImageView
import com.example.elmenuschallenge.R


class MenusListActivity : AppCompatActivity(), IMenusListController.View, TagsListener, ItemListener
{
    @Inject
    lateinit var interactor: MenusListInteractor

    private var presenter : MenusListPresenter ?= null
    private var isAttached = false

    private lateinit var mTagAdapter: TagsListAdapter
    private val mTagList = ArrayList<TagData>()

    private lateinit var mItemsAdapter : ItemsListAdapter
    private val mItemsList = ArrayList<ItemData>()

    private lateinit var mLayoutManager: LinearLayoutManager
    private var loading = false
    private var pageNumber =0


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menus_list_activity)

        (application as ElMenusChallengeApplication).networkComponent?.inject(this)
        presenter = MenusListPresenter(interactor, Schedulers.io(), AndroidSchedulers.mainThread(), WeakReference(this))

        initTagsRecyclerView()
        initItemsRecyclerView()

        //Call get tags api for first time with page number = 0
        callGetTags(pageNumber)
    }

    override fun onStart()
    {
        super.onStart()
        isAttached = true
        presenter!!.attachedView(this)
    }

    private fun initTagsRecyclerView()
    {
        mLayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        tags_horizontal_list.layoutManager = mLayoutManager

        mTagAdapter = TagsListAdapter(WeakReference(this), mTagList, this)
        tags_horizontal_list.adapter = mTagAdapter

        tags_horizontal_list.addOnScrollListener(object : RecyclerView.OnScrollListener()
        {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
            {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?

                if (!loading && linearLayoutManager!!.itemCount <= linearLayoutManager.findLastVisibleItemPosition() + 2)
                {
                    loading = true
                    pageNumber++
                    callGetTags(pageNumber)
                }
            }
        })
    }

    private fun initItemsRecyclerView()
    {
        mLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        items_vertical_list.layoutManager = mLayoutManager

        //This line to fix scroll slow when set RecyclerView inside ScrollView
        items_vertical_list.isNestedScrollingEnabled = false

        mItemsAdapter = ItemsListAdapter(WeakReference(this), mItemsList, this)
        items_vertical_list.adapter = mItemsAdapter
    }

    private fun callGetTags(pageNumber : Int)
    {
        tagsProgress.visibility = View.VISIBLE
        presenter!!.callGetTags(pageNumber)
    }

    //This method will be called when user select tag from tags list
    override fun tagClick(tagName: String)
    {
        //remove old list data and get the new data
        mItemsList.clear()
        mItemsAdapter.notifyDataSetChanged()

        itemProgress.visibility = View.VISIBLE
        presenter!!.callGetItems(tagName)
    }

    //This method will be called when user select items and it will take his to details view
    override fun clickOnItem(itemObject : ItemData, imageView : ImageView)
    {
        val intent = Intent(this@MenusListActivity, DetailsActivity::class.java)
        intent.putExtra("itemName", itemObject.getName()!!)
        intent.putExtra("itemImageUrl", itemObject.getPhotoUrl())
        intent.putExtra("itemDescription", itemObject.getDescription())
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this@MenusListActivity, imageView, ViewCompat.getTransitionName(imageView)!!
        )
        startActivity(intent, options.toBundle())
    }

    override fun hideLoader()
    {
        if(isAttached)
        {
            tagsProgress.visibility = View.GONE
            itemProgress.visibility = View.GONE
        }
    }

    override fun getTagsSuccess(tagsResponse: List<TagData>)
    {
        if(isAttached)
        {
            mTagList.addAll(tagsResponse)
            mTagAdapter.notifyDataSetChanged()
            loading = false
        }
    }

    override fun getItemsSuccess(itemsResponse: List<ItemData>)
    {
        if(isAttached)
        {
            mItemsList.addAll(itemsResponse)
            mItemsAdapter.notifyDataSetChanged()
        }
    }

    override fun handleError(msg: String)
    {
        if(isAttached)
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy()
    {
        super.onDestroy()
        isAttached = false
        presenter!!.detachedView()
    }
}
