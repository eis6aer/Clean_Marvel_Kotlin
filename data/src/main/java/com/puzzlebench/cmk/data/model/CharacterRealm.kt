package com.puzzlebench.cmk.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CharacterRealm(@PrimaryKey var id:Int? = null, var name: String? = null, var description: String? = null, var thumbnail: ThumbnailRealm? =null) : RealmObject()