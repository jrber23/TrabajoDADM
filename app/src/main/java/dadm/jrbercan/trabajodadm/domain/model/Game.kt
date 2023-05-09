package dadm.jrbercan.trabajodadm.domain.model

data class Game(val id: String, val title: String, val price: String, val thumb : String, var favourite : Boolean, val steamAppId : String?, val dealId : String)
