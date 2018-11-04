package top.rechinx.meow.ui.grid.favorite

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_grid.*
import org.koin.android.ext.android.inject
import top.rechinx.meow.R
import top.rechinx.meow.data.database.model.Manga
import top.rechinx.meow.support.viewbinding.ViewBindings
import top.rechinx.meow.support.viewbinding.bindView
import top.rechinx.meow.ui.base.BaseAdapter
import top.rechinx.meow.ui.base.BaseFragment
import top.rechinx.meow.ui.details.DetailActivity
import top.rechinx.meow.ui.grid.GridAdapter

class FavoriteFragment: BaseFragment(), FavoriteContract.View, BaseAdapter.OnItemClickListener {

    private val adapter by lazy { GridAdapter(activity!!, ArrayList()) }

    override fun initViews() {
        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        adapter.setOnItemClickListener(this)
        recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.load()
    }

    override fun onDestroy() {
        presenter.unsubscribe()
        super.onDestroy()
    }

    override fun getLayoutId(): Int = R.layout.fragment_grid

    override fun onMangasLoaded(list: List<Manga>) {
        adapter.clear()
        adapter.addAll(list)
    }

    override fun onMangasLoadError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemClick(view: View, position: Int) {
        val manga = adapter.getItem(position)
        val intent = DetailActivity.createIntent(activity!!, manga.sourceId, manga.cid!!)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ViewBindings.reset(this)
    }

    override val presenter: FavoriteContract.Presenter by inject()

}