package com.kodea.FixMaster.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.kodea.FixMaster.domain.repository.FireStoreRepository
import com.kodea.FixMaster.presentation.CategoryList.CategoryItemData
import com.kodea.FixMaster.util.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireStoreRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : FireStoreRepository {
    override fun getCategories(): Flow<Response<List<Category>>> = callbackFlow {
        Response.onLoading
        val snapshotListener =
            fireStore.collection("services")
                .addSnapshotListener { value, error ->
                val resp =
                    if (value != null) {
                        val categoryList = value.toObjects(Category::class.java)
                        Response.onSuccess<List<Category>>(categoryList)
                    } else Response.onFaillure(error?.message ?: "unknown error")
                trySend(resp).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getLimitCategories(): Flow<Response<List<Category>>> = callbackFlow {
        Response.onLoading
        val snapshotListener =
            fireStore.collection("services").limit(4)
                .addSnapshotListener { value, error ->
                    val resp =
                        if (value != null) {
                            val categoryList = value.toObjects(Category::class.java)
                            Response.onSuccess<List<Category>>(categoryList)
                        } else Response.onFaillure(error?.message ?: "unknown error")
                    trySend(resp).isSuccess
                }
        awaitClose {
            snapshotListener.remove()
        }
    }
    /*override fun getCategories(): Flow<Response<Category>> = callbackFlow {
        Response.onLoading

        val snapshotListener = fireStore.collection("services").document("painting")
            .addSnapshotListener { value, error ->
                val resp = if (value != null) {
                    val item = value.toObject(Category::class.java)
                    Response.onSuccess<Category>(item!!)

                } else Response.onFaillure(error?.message ?: error.toString())

                trySend(resp).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }

    }*/
}

data class Category(val title: String = "", val description: String = "", val icon: String = "")