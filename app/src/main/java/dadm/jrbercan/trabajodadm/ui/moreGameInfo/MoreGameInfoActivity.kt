package dadm.jrbercan.trabajodadm.ui.moreGameInfo

import android.os.Bundle
import android.text.Html
import android.util.Log
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
import dadm.jrbercan.trabajodadm.data.favouritesGames.moreGameInfo.model.SteamGameDto
import dadm.jrbercan.trabajodadm.databinding.MoreGameInfoActivityBinding
import dadm.jrbercan.trabajodadm.domain.model.GameDeals
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreGameInfoActivity: AppCompatActivity() {
    private val viewModel: MoreGameInfoViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var moreGameInfoListAdapter: MoreGameInfoListAdapter
    private lateinit var actualGame: SteamGameDto
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
            recyclerView.layoutParams.height = getTotalHeightofRecyclerView(recyclerView) //(quitar si se ralentiza significativamente la aplicación)
        }

        if (steamId != "") { // CASO: EL JUEGO CLICADO TIENE steamId
            viewModel.steamGame.observe(this@MoreGameInfoActivity) {steamGame ->
                if (steamGame.data.data == null) { caseNoSteamId(binding,thumb,title) } //CASO: LA API NO DEVUELVE INFORMACION SOBRE EL JUEGO
                else { //CASO: LA API DEVUELVE INFORMACION SOBRE EL JUEGO
                    actualGame = steamGame
                    binding.moreLessDetails.text = getString(R.string.more_details)
                    val shortDescription = Html.fromHtml(actualGame.data.data!!.short_description, Html.FROM_HTML_MODE_LEGACY).toString()
                    binding.gameDescriptionSteam.text = shortDescription
                    binding.gameDescriptionNoSteam.visibility = View.INVISIBLE
                    Glide.with(applicationContext).load(steamGame.data.data.header_image).into(binding.gameLogoSteam)
                    binding.gameLogoNoSteam.visibility = View.INVISIBLE
                    binding.gameTitleSteam.text = steamGame.data.data.name
                    binding.gameTitleNoSteam.visibility = View.INVISIBLE
                }
            }
            val language: String = resources.configuration.locales.get(0).language
            viewModel.getGameInfo(steamId,language)
        }
        else { // CASO: EL JUEGO CLICADO NO TIENE steamId
            caseNoSteamId(binding, thumb,title)
        }

        // ALTERNAR OFRECER MAYOR O MENOR DESCRIPCION DE UN JUEGO
        binding.moreLessDetails.setOnClickListener {
            if (binding.moreLessDetails.text == getString(R.string.more_details)) {
                val descriptionWithORC = Html.fromHtml(Html.fromHtml(actualGame.data.data!!.detailed_description).toString()).toString() //SE ELIMINAN LAS TAGS HTML DE LA DESCRIPCION DETALLADA
                val descriptionWithoutORC = descriptionWithORC.replace("\uFFFC", "") //SE ELIMINA EL CARACTER Object Replacement Character
                binding.gameDescriptionSteam.text = descriptionWithoutORC
                binding.moreLessDetails.text = getString(R.string.less_details)
            }
            else {
                val shortDescription = Html.fromHtml(actualGame.data.data!!.short_description, Html.FROM_HTML_MODE_LEGACY).toString()
                binding.gameDescriptionSteam.text = shortDescription
                binding.moreLessDetails.text = getString(R.string.more_details)
            }
        }
    }

    //FUNCION QUE CALCULA LA ALTURA TOTAL DEL RECYCLERVIEW (quitar si se ralentiza significativamente la aplicación)
    private fun getTotalHeightofRecyclerView(recyclerView: RecyclerView): Int {
        val adapter = recyclerView.adapter
        val itemCount = adapter?.itemCount ?: 0
        var totalHeight = 0
        for (i in 0 until itemCount) {
            val view = adapter?.createViewHolder(recyclerView, adapter.getItemViewType(i))?.itemView
            view?.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
            totalHeight += view?.measuredHeight ?: 0
        }
        return totalHeight
    }

    //FUNCION QUE MODIFICA EL LAYOUT DE LA ACTIVIDAD SI EL PARAMETRO steamId ESTA VACIO
    private fun caseNoSteamId(binding: MoreGameInfoActivityBinding, thumb: String, title: String) {
        binding.moreLessDetails.visibility = View.INVISIBLE
        binding.gameDescriptionNoSteam.text = getString(R.string.no_game_description)
        binding.gameDescriptionSteam.visibility = View.INVISIBLE
        Glide.with(applicationContext).load(thumb).into(binding.gameLogoNoSteam)
        binding.gameLogoSteam.visibility = View.INVISIBLE
        binding.gameTitleSteam.visibility = View.INVISIBLE
        binding.gameTitleNoSteam.text = title
    }

}