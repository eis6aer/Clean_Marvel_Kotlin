package com.puzzlebench.cmk.data.repository.source

import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults

abstract class DataSource<T : RealmObject>(private val clazz: Class<T>) {

    fun save(item: T, realm: Realm? = null) {
        if (realm == null) {
            Realm.getDefaultInstance().executeTransaction({
                it.insertOrUpdate(item)
            })
        } else {
            realm.insertOrUpdate(item)
        }
    }

    fun save(items: List<T>, realm: Realm = Realm.getDefaultInstance()) {
        realm.executeTransaction({
            items.map { save(it, realm) }
        })
    }

    fun getAll(realm: Realm = Realm.getDefaultInstance()): RealmResults<T>? {
        var results: RealmResults<T>? = null
        realm.executeTransaction({
            results = it.where(clazz).findAll()
        })
        return results
    }

    fun getById(characterId: Int, realm: Realm = Realm.getDefaultInstance()) : T
    {
        var results: T? = null
        realm.executeTransaction({
            results = it.where(clazz).equalTo("id",characterId).findFirst()
        })
        return results!!
    }
}