import retrofit2.http.GET
import com.example.grupodos_dam.utils.Constants.END_POINT
import com.example.grupodos_dam.webservice.PokemonResponse

interface ApiService {
    @GET(END_POINT)
    suspend fun getPokemonList(): PokemonResponse
}

