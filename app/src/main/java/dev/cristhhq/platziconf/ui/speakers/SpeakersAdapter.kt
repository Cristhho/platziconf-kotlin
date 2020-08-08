package dev.cristhhq.platziconf.ui.speakers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.cristhhq.platziconf.R
import dev.cristhhq.platziconf.model.Speaker

class SpeakersAdapter(val speakerListener: SpeakerListener): RecyclerView.Adapter<SpeakersAdapter.ViewHolder>() {

    var listSpeakers = ArrayList<Speaker>()

    fun updateData(data: List<Speaker>) {
        listSpeakers.clear()
        listSpeakers.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_speaker, parent, false))

    override fun getItemCount() = listSpeakers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val speaker = listSpeakers[position] as Speaker
        holder.speakerName.text = speaker.name
        holder.speakerJob.text = speaker.workplace

        Glide.with(holder.itemView.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.speakerImage)

        holder.itemView.setOnClickListener {
            speakerListener.onSpeakerClicked(speaker, position)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val speakerImage = itemView.findViewById<ImageView>(R.id.ivSpeakerImage)
        val speakerName = itemView.findViewById<TextView>(R.id.tvSpeakerName)
        val speakerJob = itemView.findViewById<TextView>(R.id.tvSpeakerJob)
    }

    interface SpeakerListener {
        fun onSpeakerClicked(speaker: Speaker, position: Int)
    }
}