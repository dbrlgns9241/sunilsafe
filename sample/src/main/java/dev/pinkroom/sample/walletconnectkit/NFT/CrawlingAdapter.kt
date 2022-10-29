package dev.pinkroom.sample.walletconnectkit.NFT

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.pinkroom.sample.walletconnectkit.R
import kotlinx.android.synthetic.main.crawling_item.view.*



class CrawlingAdapter (var items : ArrayList<CrawlingItem>) : RecyclerView.Adapter<CrawlingAdapter.ViewHolder> () {
    //  crawling_item.xml과 연결한 뷰 홀더 반환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.crawling_item, parent, false)

        return ViewHolder(itemView)
    }

    // position번째 데이터와 xml 연결
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)

//        holder.itemView.imagebtn.setOnClickListener {
//            Log.d("Tssssss", "여기까진 됨")
//            val intent = Intent(holder.itemView.imagebtn.context, ImageViewActivity::class.java)
//            intent.putExtra("data", item.image)
//            startActivity(holder.itemView.imagebtn.context, intent, null)
//        }
    }

    // 아이템 갯수 반환
    override fun getItemCount() = items.count()

    // 데이터와 xml 연결
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun setItem(item : CrawlingItem) {
            itemView.title.text = item.title
            itemView.ether.text = item.ether
            // 이미지 url 읽어서 넣기
            Glide.with(itemView)
                .load(item.image)
                .into(itemView.imagebtn)

            itemView.imagebtn.setOnClickListener {
                Log.d("Tssssss", item.image)
//                Intent(itemView.imagebtn.context, ImageViewActivity::class.java).apply {
//                    putExtra("data", item.image)
//                }.run { itemView.imagebtn.context.startActivity(this) }
                val intent = Intent(itemView.imagebtn.context, ImageViewActivity::class.java)
                intent.putExtra("data", item.image)
                intent.putExtra("data2", item.title)
                intent.putExtra("data3", item.ether)
                startActivity(itemView.imagebtn.context, intent, null)
            }
        }
    }

}