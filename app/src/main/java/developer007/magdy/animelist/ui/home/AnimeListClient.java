package developer007.magdy.animelist.ui.home;

import java.util.List;

import developer007.magdy.animelist.data.API;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimeListClient {
    API api = new API();

    public final String Base_URL = api.baseUrl;
    private AnimeListInterfaces animeListInterfaces;
    private static AnimeListClient INSTANCE;

    public AnimeListClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        animeListInterfaces = retrofit.create(AnimeListInterfaces.class);

    }

    public static AnimeListClient getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new AnimeListClient();
        }
        return INSTANCE;
    }

    public Call<AnimeListModule> getAnime(String name) {

        return animeListInterfaces.getAnime(name);
    }
}
