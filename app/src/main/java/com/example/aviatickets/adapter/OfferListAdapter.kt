package com.example.aviatickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aviatickets.R
import com.example.aviatickets.databinding.ItemOfferBinding
import com.example.aviatickets.model.entity.Offer
import com.example.aviatickets.model.service.FakeService.offerList

class OfferListAdapter : RecyclerView.Adapter<OfferListAdapter.ViewHolder>() {

    private val items: ArrayList<Offer> = arrayListOf()

    fun setItems(offerList: List<Offer>) {

        val diffResult = DiffUtil.calculateDiff(OffersCallBack(items, offerList ))
        items.clear()
        items.addAll(offerList)
        diffResult.dispatchUpdatesTo(this)


    }
    private fun sortWithDiffUtil(sortedList: List<Offer>) {
        val diffResult = DiffUtil.calculateDiff(OffersCallBack(items, sortedList))
        items.clear()
        items.addAll(sortedList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun sortByPrice(){
        val sortedList = items.sortedBy { it.price }
        sortWithDiffUtil(sortedList)
    }

    fun sortByDuration(){
        val sortedList = items.sortedBy { it.flight.duration }
        sortWithDiffUtil(sortedList)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOfferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(items[position])
    }

    inner class ViewHolder(
        private val binding: ItemOfferBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(offer: Offer) {
            val flight = offer.flight

            with(binding) {
                departureTime.text = flight.departureTimeInfo
                arrivalTime.text = flight.arrivalTimeInfo
                route.text = context.getString(
                    R.string.route_fmt,
                    flight.departureLocation.code,
                    flight.arrivalLocation.code
                )
                duration.text = context.getString(
                    R.string.time_fmt,
                    getTimeFormat(flight.duration).first.toString(),
                    getTimeFormat(flight.duration).second.toString()
                )
                direct.text = context.getString(R.string.direct)
                price.text = context.getString(R.string.price_fmt, offer.price.toString())
            }
        }

        private fun getTimeFormat(minutes: Int): Pair<Int, Int> = Pair(
            first = minutes / 60,
            second = minutes % 60
        )

    }


}