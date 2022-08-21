package hacku.cookbot;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;

import java.io.IOException;

import hacku.cookbot.databinding.FragmentThirdBinding;

public class ThirdFragment extends FirstFragment{
    private FragmentThirdBinding binding;
    private RecipeAPI recipeAPI;
    private OkHttpClient okHttpClient = new OkHttpClient();
    public static String recipeUrl;
    private String MaterialName;
    private MaterialName_MaterialCategoryId MCL;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
            
    ) {
        super.onCreate(savedInstanceState);
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        /*
        try {
            MCL = new MaterialName_MaterialCategoryId();
            MCL.set();
            MCL.setMaterialName(MaterialName);
            MCL.setRecipeId();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Request request = new Request.Builder()
                .url("https://app.rakuten.co.jp/services/api/Recipe/CategoryRanking/20170426?format=String&applicationId=1048445474139185419&elements=mediumImageUrl%2CrecipeCost%2CrecipeId%2CrecipeIndication%2CrecipeUrl&categoryId=" + MCL.getCategoryId())
                .addHeader("Accept", "text/plain")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Request request, IOException e) {
                Log.d("okhttp", "Request failed " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Response response) throws IOException {
                result = response.body().string();

                //追加["]の前に[\]を追加する(recipeAPIのセットアップ)
            String setUpResult = "";
            int i;
            for (i = 0; i < result.length(); i++) {
                if (result.charAt(i) == '\"') {
                    setUpResult += "\\";
                }
                setUpResult += result.charAt(i);
            }
            result = setUpResult;
            System.out.println("after=" + result);

                System.out.println("after=" + result);
                try {
                    recipeAPI.setUp(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
*/

        try {
            Thread.sleep(2000);
            System.out.println("before=" + result);
            recipeAPI = new RecipeAPI();
            recipeAPI.setUp(result);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        binding.picture1.setWebViewClient(new WebViewClient());
        binding.picture1.getSettings().setJavaScriptEnabled(true);
        binding.picture2.setWebViewClient(new WebViewClient());
        binding.picture2.getSettings().setJavaScriptEnabled(true);
        binding.picture3.setWebViewClient(new WebViewClient());
        binding.picture3.getSettings().setJavaScriptEnabled(true);
        binding.picture4.setWebViewClient(new WebViewClient());
        binding.picture4.getSettings().setJavaScriptEnabled(true);

        try {
            binding.picture1.loadUrl(recipeAPI.getFirstString("mediumImageUrl"));
            binding.picture2.loadUrl(recipeAPI.getSecondString("mediumImageUrl"));
            binding.picture3.loadUrl(recipeAPI.getThirdString("mediumImageUrl"));
            binding.picture4.loadUrl(recipeAPI.getFourthString("mediumImageUrl"));
            String text1 = recipeAPI.getFirstString("recipeTitle")
                    + "\n費用：" + recipeAPI.getFirstString("recipeCost")
                    + "\n調理時間：" + recipeAPI.getFirstString("recipeIndication");
            String text2 = recipeAPI.getSecondString("recipeTitle")
                    + "\n費用：" + recipeAPI.getSecondString("recipeCost")
                    + "\n調理時間：" + recipeAPI.getSecondString("recipeIndication");
            String text3 = recipeAPI.getThirdString("recipeTitle")
                    + "\n費用：" + recipeAPI.getThirdString("recipeCost")
                    + "\n調理時間：" + recipeAPI.getThirdString("recipeIndication");
            String text4 = recipeAPI.getFourthString("recipeTitle")
                    + "\n費用：" + recipeAPI.getFourthString("recipeCost")
                    + "\n調理時間：" + recipeAPI.getFourthString("recipeIndication");
            binding.textView1.post(() -> binding.textView1.setText(text1));
            binding.textView2.post(() -> binding.textView2.setText(text2));
            binding.textView3.post(() -> binding.textView3.setText(text3));
            binding.textView4.post(() -> binding.textView4.setText(text4));

        } catch (JSONException e) {
            e.printStackTrace();
        }



        binding.buttonThirdPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FirstFragment);
            }
        });

        binding.buttonRequest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    recipeUrl = recipeAPI.getFirstString("recipeUrl");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FourthFragment);
            }

        });
        binding.buttonRequest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    recipeUrl = recipeAPI.getSecondString("recipeUrl");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FourthFragment);
            }

        });
        binding.buttonRequest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    recipeUrl = recipeAPI.getThirdString("recipeUrl");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FourthFragment);
            }

        });
        binding.buttonRequest4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    recipeUrl = recipeAPI.getFourthString("recipeUrl");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FourthFragment);
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
