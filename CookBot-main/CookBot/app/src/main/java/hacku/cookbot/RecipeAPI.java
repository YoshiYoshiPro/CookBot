package hacku.cookbot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecipeAPI {

	JSONObject json;
	JSONObject datas1;
	JSONObject datas2;
	JSONObject datas3;
	JSONObject datas4;
	JSONArray resultJson;
	String item;

	RecipeAPI() throws JSONException {
/*	String str="{"
			+ "  \"result\": ["
			+ "    {"
			+ "      \"foodImageUrl\": \"https://image.space.rakuten.co.jp/d/strg/ctrl/3/66fc7af1b72e6955647b36f1434950cde8a37e14.26.2.3.2.jpg\","
				+ "      \"mediumImageUrl\": \"https://image.space.rakuten.co.jp/d/strg/ctrl/3/66fc7af1b72e6955647b36f1434950cde8a37e14.26.2.3.2.jpg?thum=54\","
			+ "      \"nickname\": \"ななじまる\","
			+ "      \"pickup\": 1,"
			+ "      \"rank\": \"1\","
			+ "      \"recipeCost\": \"300円前後\","
			+ "      \"recipeDescription\": \"食べだすと、とまりません･:*+٩(๑❛ᴗ❛๑)۶.:+"
			+ "里芋に片栗粉をまぶして揚げたものを、甘辛たれで絡めて仕上げました♪\","
			+ "      \"recipeId\": 1950012560,"
			+ "      \"recipeIndication\": \"約15分\","
			+ "      \"recipeMaterial\": ["
			+ "        \"里芋\","
			+ "        \"片栗粉\","
			+ "        \"●醤油\","
			+ "        \"●砂糖\","
			+ "        \"●みりん\","
			+ "        \"●酢\","
			+ "        \"揚げ油\""
			+ "      ],"
			+ "      \"recipePublishday\": \"2017/01/24 12:52:49\","
			+ "      \"recipeTitle\": \"食べだすと、とまらない☆揚げ里芋の甘辛！\","
			+ "      \"recipeUrl\": \"https://recipe.rakuten.co.jp/recipe/1950012560/\","
			+ "      \"shop\": 0,"
			+ "      \"smallImageUrl\": \"https://image.space.rakuten.co.jp/d/strg/ctrl/3/66fc7af1b72e6955647b36f1434950cde8a37e14.26.2.3.2.jpg?thum=55\""
			+ "    },"
			+ "    {"
			+ "      \"foodImageUrl\": \"https://image.space.rakuten.co.jp/d/strg/ctrl/3/fbd7dd260d736654532e6c0b1ec185a0cede8675.49.2.3.2.jpg\","
			+ "      \"mediumImageUrl\": \"https://image.space.rakuten.co.jp/d/strg/ctrl/3/fbd7dd260d736654532e6c0b1ec185a0cede8675.49.2.3.2.jpg?thum=54\","
			+ "      \"nickname\": \"はぁぽじ\","
			+ "      \"pickup\": 0,"
			+ "      \"rank\": \"2\","
			+ "      \"recipeCost\": \"300円前後\","
			+ "      \"recipeDescription\": \"そのままでも、ご飯にのせて丼にしても♪\","
			+ "      \"recipeId\": 1760028309,"
			+ "      \"recipeIndication\": \"約10分\","
			+ "      \"recipeMaterial\": ["
			+ "        \"鶏むね肉\","
			+ "        \"塩\","
			+ "        \"酒\","
			+ "        \"片栗粉\","
			+ "        \"○水\","
			+ "        \"○塩\","
			+ "        \"○鶏がらスープの素\","
			+ "        \"○黒胡椒\","
			+ "        \"長ネギ\","
			+ "        \"いりごま\","
			+ "        \"ごま油\""
			+ "      ],"
			+ "      \"recipePublishday\": \"2017/10/10 22:37:34\","
			+ "      \"recipeTitle\": \"ご飯がすすむ！鶏むね肉のねぎ塩焼き\","
			+ "      \"recipeUrl\": \"https://recipe.rakuten.co.jp/recipe/1760028309/\","
			+ "      \"shop\": 0,"
			+ "      \"smallImageUrl\": \"https://image.space.rakuten.co.jp/d/strg/ctrl/3/fbd7dd260d736654532e6c0b1ec185a0cede8675.49.2.3.2.jpg?thum=55\""
			+ "    },"
			+ "    {"
			+ "      \"foodImageUrl\": \"https://image.space.rakuten.co.jp/d/strg/ctrl/3/5bab30010b628b9d02a78e4673f273dd1bf3d4d2.86.2.3.2.jpg\","
			+ "      \"mediumImageUrl\": \"https://image.space.rakuten.co.jp/d/strg/ctrl/3/5bab30010b628b9d02a78e4673f273dd1bf3d4d2.86.2.3.2.jpg?thum=54\","
			+ "      \"nickname\": \"我家の☆毎日ゴハン\","
			+ "      \"pickup\": 1,"
			+ "      \"rank\": \"3\","
			+ "      \"recipeCost\": \"300円前後\","
			+ "      \"recipeDescription\": \"香ばしくて甘いお芋が、レンジを使うので手早く食べられますよ♪\","
			+ "      \"recipeId\": 1010004368,"
			+ "      \"recipeIndication\": \"約10分\","
			+ "      \"recipeMaterial\": ["
			+ "        \"さつま芋\","
			+ "        \"油\","
			+ "        \"砂糖\","
			+ "        \"塩\""
			+ "      ],"
			+ "      \"recipePublishday\": \"2012/01/28 02:59:44\","
			+ "      \"recipeTitle\": \"すぐ食べたい時に！ さつまいもスティック♡\","
			+ "      \"recipeUrl\": \"https://recipe.rakuten.co.jp/recipe/1010004368/\","
			+ "      \"shop\": 0,"
			+ "      \"smallImageUrl\": \"https://image.space.rakuten.co.jp/d/strg/ctrl/3/5bab30010b628b9d02a78e4673f273dd1bf3d4d2.86.2.3.2.jpg?thum=55\""
			+ "    },"
			+ "    {"
			+ "      \"foodImageUrl\": \"https://image.space.rakuten.co.jp/d/strg/ctrl/3/3e5906a3607b2f1321cda1158b251c4223204420.40.2.3.2.jpg\","
			+ "      \"mediumImageUrl\": \"https://image.space.rakuten.co.jp/d/strg/ctrl/3/3e5906a3607b2f1321cda1158b251c4223204420.40.2.3.2.jpg?thum=54\","
			+ "      \"nickname\": \"*ももら*\","
			+ "      \"pickup\": 1,"
			+ "      \"rank\": \"4\","
			+ "      \"recipeCost\": \"300円前後\","
			+ "      \"recipeDescription\": \"鶏胸肉なのにウイング風♬骨が無いので食べ易くお弁当にもピッタリ♡油で揚げないのでヘルシーです♪\","
			+ "      \"recipeId\": 1400014946,"
			+ "      \"recipeIndication\": \"約10分\","
			+ "      \"recipeMaterial\": ["
			+ "        \"鶏むね肉\","
			+ "        \"片栗粉\","
			+ "        \"塩コショウ\","
			+ "        \"炒め用サラダ油\","
			+ "        \"A醤油\","
			+ "        \"Aみりん\","
			+ "        \"Aお酒\","
			+ "        \"A砂糖\""
			+ "      ],"
			+ "      \"recipePublishday\": \"2015/09/30 14:28:09\","
			+ "      \"recipeTitle\": \"鶏胸肉で簡単♪手羽風揚げない甘辛照焼チキンお弁当に\","
			+ "      \"recipeUrl\": \"https://recipe.rakuten.co.jp/recipe/1400014946/\","
			+ "      \"shop\": 0,"
			+ "      \"smallImageUrl\": \"https://image.space.rakuten.co.jp/d/strg/ctrl/3/3e5906a3607b2f1321cda1158b251c4223204420.40.2.3.2.jpg?thum=55\""
			+ "    }"
			+ "  ]"
			+ "}";

	this.json=new JSONObject(str);

		//結果を取得
		resultJson = json.getJSONArray("result");
		//1位から4位を別々に保存
		datas1 = resultJson.getJSONObject(0);
		datas2 = resultJson.getJSONObject(1);
		datas3 = resultJson.getJSONObject(2);
		datas4 = resultJson.getJSONObject(3);
*/

	}
/*	RecipeAPI(String js) throws JSONException {
		System.out.println(js);
		this.json = new JSONObject(js);

		//結果を取得
		resultJson = json.getJSONArray("result");
		//1位から4位を別々に保存
		datas1 = resultJson.getJSONObject(0);
		datas2 = resultJson.getJSONObject(1);
		datas3 = resultJson.getJSONObject(2);
		datas4 = resultJson.getJSONObject(3);
		System.out.println("datas1="+datas1);
		System.out.println("datas2="+datas2);
		System.out.println("datas3="+datas3);
		System.out.println("datas4="+datas4);
	}
*/
	public void setUp(String js) throws JSONException {
		System.out.println(js);
		this.json = new JSONObject(js);

		//結果を取得
		resultJson = json.getJSONArray("result");
		//1位から4位を別々に保存
		datas1 = resultJson.getJSONObject(0);
		datas2 = resultJson.getJSONObject(1);
		datas3 = resultJson.getJSONObject(2);
		datas4 = resultJson.getJSONObject(3);
		System.out.println("datas1="+datas1);
		System.out.println("datas2="+datas2);
		System.out.println("datas3="+datas3);
		System.out.println("datas4="+datas4);
	}







	//	setter
	//item
	public void setItem(String item) {
		this.item=item;
		System.out.println(this.item);
	}


	//	getter
	//	Stringの取得（recipeMaterial以外）
	public String getFirstRecipeData() throws JSONException {
	    String data=datas1.getString(this.item);
	    System.out.println("datas1="+data);
	    return data;
	}
	public String getSecondRecipeData() throws JSONException {
	    String data=datas2.getString(this.item);
	    return data;
	}
	public String getThirdRecipeData() throws JSONException {
	    String data=datas3.getString(this.item);
	    return data;
	}
	public String getFourthRecipeData() throws JSONException {
	    String data=datas4.getString(this.item);
	    return data;
	}

	//	String[]の取得
	public String[] getFirstRecipeMaterial() throws JSONException {
		JSONArray recipeMaterial=datas1.getJSONArray("recipeMaterial");
		String[] datas=new String[recipeMaterial.length()];
		for(int i=0;i<recipeMaterial.length();i++) {
			String data=recipeMaterial.getString(i);
			datas[i]=data;
		}

		return datas;
	}
	public String[] getSecondRecipeMaterial() throws JSONException {
		JSONArray recipeMaterial=datas2.getJSONArray("recipeMaterial");
		String[] datas=new String[recipeMaterial.length()];
		for(int i=0;i<recipeMaterial.length();i++) {
			String data=recipeMaterial.getString(i);
			datas[i]=data;
		}

		return datas;
	}
	public String[] getThirdRecipeMaterial() throws JSONException {
		JSONArray recipeMaterial=datas3.getJSONArray("recipeMaterial");
		String[] datas=new String[recipeMaterial.length()];
		for(int i=0;i<recipeMaterial.length();i++) {
			//文字を格納
			String data=recipeMaterial.getString(i);

			//記号を除去
			String newData = data
					.replace("★", "")
					.replace("☆", "")
					.replace("●", "")
					.replace("○", "")
					.replace("A", "")
					.replace("◎", "");

			char firstWord=newData.charAt(0);

			//複数レシピがあるときのタイトルをリストから除去
			if(firstWord == '【'){}

			else{
				datas[i]=newData;
			}


		}

		return datas;
	}
	public String[] getFourthRecipeMaterial() throws JSONException {
		JSONArray recipeMaterial=datas4.getJSONArray("recipeMaterial");
		String[] datas=new String[recipeMaterial.length()];
		for(int i=0;i<recipeMaterial.length();i++) {
			String data=recipeMaterial.getString(i);
			datas[i]=data;
		}

		return datas;
	}
	//	intの取得
	public int getFirstRecipeInt() throws JSONException {
		int d=datas1.getInt(item);
		return d;
	}
	public int getSecondRecipeInt() throws JSONException {
		int d=datas2.getInt(item);
		return d;
	}
	public int getThirdRecipeInt() throws JSONException {
		int d=datas3.getInt(item);
		return d;
	}
	public int getFourthRecipeInt() throws JSONException {
		int d=datas4.getInt(item);
		return d;
	}

	//	あわせ命令
	//	setItem+getString
	public String getFirstString(String item) throws JSONException {
		System.out.println("あいうえお");
		System.out.println(item);
		this.setItem(item);
		return this.getFirstRecipeData();
	}
	public String getSecondString(String item) throws JSONException {
		this.setItem(item);
		return this.getSecondRecipeData();
	}
	public String getThirdString(String item) throws JSONException {
		this.setItem(item);
		return this.getThirdRecipeData();

	}
	public String getFourthString(String item) throws JSONException {
		this.setItem(item);
		return this.getFourthRecipeData();
	}

	//	setItem+getString[]
	public String[] getFirstStringArray(String item) throws JSONException {
		return this.getFirstRecipeMaterial();
	}
	public String[] getSecondArray(String item) throws JSONException {
		return this.getSecondRecipeMaterial();
	}
	public String[] getThirdArray(String item) throws JSONException {
		return this.getThirdRecipeMaterial();
	}
	public String[] getFourthArray(String item) throws JSONException {
		return this.getFourthRecipeMaterial();
	}

	//	setItem+getInt
	public int getFirstInt(String item) throws JSONException {
		this.setItem(item);
		return this.getFirstRecipeInt();
	}
	public int getSecondInt(String item) throws JSONException {
		this.setItem(item);
		return this.getSecondRecipeInt();
	}
	public int getThirdInt(String item) throws JSONException {
		this.setItem(item);
		return this.getThirdRecipeInt();
	}
	public int getFourthInt(String item) throws JSONException {
		this.setItem(item);
		return this.getFourthRecipeInt();
	}


}
