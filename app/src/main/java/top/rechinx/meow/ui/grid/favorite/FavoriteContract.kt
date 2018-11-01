package top.rechinx.meow.ui.grid.favorite

import top.rechinx.meow.data.database.model.Manga
import top.rechinx.meow.support.mvp.BasePresenter
import top.rechinx.meow.support.mvp.BaseView

interface FavoriteContract {

    interface View: BaseView<Presenter> {

        fun onMangasLoaded(list: List<Manga>)

        fun onMangasLoadError()
    }

    interface Presenter: BasePresenter<View> {

        fun load()

    }

}