package hacku.cookbot;

public class RecipeAPIInfo {
/*
	String item;{
	item="nickname";
*/
	//	実行方法は以下の2通り
	// ...は"First"of"Second"or"Third"or"Fourth"

		//1つ目
			/* 1.itemは下記のいずれかをセットしてください（api.setItem(item)）
			 * 2.命令を実行（入力なし）
			 *
			 * 出力：String
				foodImageUrl
				mediumImageUrl
				nickname
				recipeCost
				recipeDescription
				recipeIndication
				recipePublishday
				recipeTitle
				recipeUrl
				shop
				smallImageUrl
				その後 api.get...RecipeData() を実行
			 * 出力：String[]
				recipeMaterial
				その後 api.get...RecipeMaterial() を実行
			 * 出力：int
			 	recipeid
			 	pickup
			 	その後 api.get...RecipeInt() を実行
			*/

		//2つ目
			/*1.命令を実行（入力あり(String item)）
			 *
			 * 出力：String
			 	api.get...RecipeString(item) を実行
			 * 出力：String[]
				api.get...RecipeStringArray(item) を実行
			 * 出力：int
				api.get...RecipeInt(item) を実行
			 */
/*
	//	例
		this.api.setItem(item);
		String str1=api.getFirstRecipeData();
		System.out.println(str1);
	//	と
		String str2=api.getFirstString("nickname");
		System.out.println(str2);
	//	は同じ出力になります。
*/




}


