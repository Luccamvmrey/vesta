package com.example.vesta.domain

import android.content.ContentValues.TAG
import android.util.Log
import com.example.vesta.data.condo.getCondoFromCondoId
import com.example.vesta.data.condo.models.Story
import com.example.vesta.data.condo.models.Tower
import com.example.vesta.data.condo.updateTowersOnCondo
import com.google.firebase.firestore.FirebaseFirestore

fun createEmptyStory(storyNumber: Int): Story {
    return Story(storyNumber, false, ArrayList())
}

fun createTower(towerNumber: Int,numberOfStories: Int): Tower {
    val stories = ArrayList<Story>()

    for (i in 1..numberOfStories) {
        stories.add(createEmptyStory(i))
    }

    return Tower(towerNumber.toString(), stories)
}

fun populateCondoWithTowersAndStories(db: FirebaseFirestore, condoId: String) {
    getCondoFromCondoId(db, condoId) {condo, _ ->
        val towers = ArrayList<Tower>()

        for (i in 1..condo?.numberOfTowers!!) {
            towers.add(createTower(i, condo.numberOfStoriesPerTower!!))
        }

        updateTowersOnCondo(db, condoId, towers) {
            Log.d(TAG, "All fine here")
        }
    }
}