package dadm.jrbercan.trabajodadm.ui.moreGameInfo

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dadm.jrbercan.trabajodadm.R
import dadm.jrbercan.trabajodadm.databinding.MoreGameInfoActivityBinding
import dadm.jrbercan.trabajodadm.domain.model.GameDeals
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreGameInfoActivity: AppCompatActivity() {
    private val viewModel: MoreGameInfoViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var moreGameInfoListAdapter: MoreGameInfoListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MoreGameInfoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("GAME_TITLE")!!
        val id = intent.getStringExtra("GAME_ID")!!
        val steamId = intent.getStringExtra("GAME_STEAM_ID")?:""
        val thumb = intent.getStringExtra("GAME_THUMB")!!

        viewModel.getGameDeals(id)

        recyclerView = binding.moreGameInfoRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        moreGameInfoListAdapter = MoreGameInfoListAdapter(emptyList())
        recyclerView.adapter = moreGameInfoListAdapter

        viewModel.gameDealsWithStoreName.observe(this){list ->
            moreGameInfoListAdapter = MoreGameInfoListAdapter(list)
            recyclerView.adapter = moreGameInfoListAdapter
        }


        if (steamId != "") {
            viewModel.steamGame.observe(this@MoreGameInfoActivity) {steamGame ->
                //val descriptionWithORC = Html.fromHtml(Html.fromHtml(steamGame.data.data.detailed_description).toString()).toString() //SE ELIMINAN LAS TAGS HTML DE LA DESCRIPCION DETALLADA
                //val descriptionWithoutORC = descriptionWithORC.replace("\uFFFC", "") //SE ELIMINA EL CARACTER Object Replacement Character
                //binding.gameDescriptionSteam.text = descriptionWithoutORC
                binding.gameDescriptionSteam.text = steamGame.data.data.short_description
                binding.gameDescriptionNoSteam.visibility = View.INVISIBLE
                Glide.with(applicationContext).load(steamGame.data.data.header_image).into(binding.gameLogoSteam)
                binding.gameLogoNoSteam.visibility = View.INVISIBLE
                binding.gameTitleSteam.text = steamGame.data.data.name
                binding.gameTitleNoSteam.visibility = View.INVISIBLE
            }
            viewModel.getGameInfo(steamId)
        }
        else {
            binding.gameDescriptionNoSteam.text = getString(R.string.no_game_description)
            binding.gameDescriptionSteam.visibility = View.INVISIBLE
            Glide.with(applicationContext).load(thumb).into(binding.gameLogoNoSteam)
            binding.gameLogoSteam.visibility = View.INVISIBLE
            binding.gameTitleSteam.visibility = View.INVISIBLE
            binding.gameTitleNoSteam.text = title
        }
    }
}