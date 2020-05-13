package com.android.mobile.util.rx

import androidx.appcompat.widget.SearchView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Iffan on 13/05/20.
 */
class RxSearchObservable {

    companion object {
        fun fromView(searchView: SearchView): Observable<String> {
            val subject = PublishSubject.create<String>()

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    subject.onComplete()
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    subject.onNext(newText)
                    return true
                }

            })

            return subject
        }
    }
}