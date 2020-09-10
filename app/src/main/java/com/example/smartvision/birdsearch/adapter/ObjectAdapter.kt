/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.smartvision.birdsearch.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.smartvision.R
import com.example.smartvision.birdsearch.model.Bird
import com.example.smartvision.birdsearch.adapter.ObjectAdapter.BirdViewHolder
import com.google.mlkit.md.productsearch.ImageDownloadTask

/** Presents the list of product items from cloud product search.  */
class ObjectAdapter(private val birdList: List<Bird>,private val callbacks: AdapterCallbacks) : RecyclerView.Adapter<BirdViewHolder>() {

    class BirdViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {

        private val imageView: ImageView = view.findViewById(R.id.product_image)
        private val titleView: TextView = view.findViewById(R.id.product_title)
        private val subtitleView: TextView = view.findViewById(R.id.product_subtitle)
        private val container: ConstraintLayout = view.findViewById(R.id.container)
        private val imageSize: Int = view.resources.getDimensionPixelOffset(R.dimen.product_item_image_size)

        fun bindProduct(bird: Bird,callbacks: AdapterCallbacks) {
            imageView.setImageDrawable(null)
            if (!TextUtils.isEmpty(bird.imageUrl)) {
                ImageDownloadTask(imageView, imageSize).execute(bird.imageUrl)
            } else {
                imageView.setImageResource(R.drawable.ic_picture_fallback)
            }
            titleView.text = bird.title
            subtitleView.text = bird.subtitle

            container.setOnClickListener {
                callbacks.onClickCallback(bird)
            }
        }

        companion object {
            fun create(parent: ViewGroup) =
                BirdViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirdViewHolder =
        BirdViewHolder.create(parent)

    override fun onBindViewHolder(holder: BirdViewHolder, position: Int) {
        holder.bindProduct(birdList[position],callbacks)
    }

    override fun getItemCount(): Int = birdList.size
}
