package insights;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ContentItem.Language;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ProfileOptions;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;

import insights.MySQL;

public class Insights_lib {
		PersonalityInsights service;
		ProfileOptions options= null;
		Profile profile;
		Profile result = null;
		String text;

		
		public Insights_lib(){	
			service = new PersonalityInsights("2018-10-19");
			IamOptions iamOptions = new IamOptions.Builder()
				    .apiKey("0vjkDauOjcfqPwxFpjWOspaQGC8WVbvVpiZ2GU47P5AZ")
				    .build();
			service.setIamCredentials(iamOptions);
   }

		
		public void keisan(){
		/*	text = "Call me Ishmael. Some years ago-never mind how long precisely-having "
				    + "little or no money in my purse, and nothing particular to interest me on shore, "
				    + "I thought I would sail about a little and see the watery part of the world. "
				    + "It is a way I have of driving off the spleen and regulating the circulation. "
				    + "Whenever I find myself growing grim about the mouth; whenever it is a damp, "
				    + "drizzly November in my soul; whenever I find myself involuntarily pausing before "
				    + "coffin warehouses, and bringing up the rear of every funeral I meet; and especially "
				    + "whenever my hypos get such an upper hand of me, that it requires a strong moral "
				    + "principle to prevent me from deliberately stepping into the street, and methodically "
				    + "knocking people's hats off-then, I account it high time to get to sea as soon as I can. "
				    + "This is my substitute for pistol and ball. With a philosophical flourish Cato throws himself "
				    + "upon his sword; I quietly take to the ship. There is nothing surprising in this. "
				    + "If they but knew it, almost all men in their degree, some time or other, cherish "
				    + "very nearly the same feelings towards the ocean with me. There now is your insular "
				    + "city of the Manhattoes, belted round by wharves as Indian isles by coral reefs-commerce surrounds "
				    + "it with her surf. Right and left, the streets take you waterward.";*/
		
			text = 	"安倍総理が１４日、ロシアのプーチン大統領との首脳会談で、日ソ共同宣言を基礎に平和条約の締結交渉を加速させる方針で一致したことを受けて、"
					+ "政界では波紋が広がっています。安倍晋三首相は１８日夜、シンガポール、オーストラリア、パプアニューギニアの３カ国歴訪を終え、政府専用機で羽田空港に帰国した。"
					+ "５日間の歴訪で「自由で開かれたインド太平洋」構想や自由貿易推進の意義を重ねて発信し、各国首脳との会談も積極的にこなした。しかし、韓国の文在寅（ムン・ジェイン）"
					+ "大統領との会談はなかった。元徴用工による訴訟で韓国最高裁が日本企業に賠償を命じた判決への対応を示せない文氏と会談しても無意味だと判断、「戦略的放置」に徹したようだ。";
			
		 options = new ProfileOptions.Builder()
	  .contentLanguage(Language.JA) //日本語入力
		 .acceptLanguage(Language.JA)  //日本語出力
		  .text(text)
		  .build();
		}

		
public void view() {
	 profile = service.profile(options).execute();
	//System.out.println(profile);
	 ObjectMapper mapper = new ObjectMapper();
	 JsonNode node = null;
		try {
			node = mapper.readTree(String.valueOf(profile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	double openness = node.get("personality").get(0).get("percentile").doubleValue();
	double conscientiousness = node.get("personality").get(1).get("percentile").doubleValue();
	double extraversion = node.get("personality").get(2).get("percentile").doubleValue();
	double agreeableness = node.get("personality").get(3).get("percentile").doubleValue();
	double neuroticism = -1;
	
	double adventurousness = node.get("personality").get(0).get("children").get(0).get("percentile").doubleValue();
	double artistic = node.get("personality").get(0).get("children").get(1).get("percentile").doubleValue();
	double emotionality = node.get("personality").get(0).get("children").get(2).get("percentile").doubleValue();
	double imagination = node.get("personality").get(0).get("children").get(3).get("percentile").doubleValue();
	double intellect = node.get("personality").get(0).get("children").get(4).get("percentile").doubleValue();
	double challenging = node.get("personality").get(0).get("children").get(5).get("percentile").doubleValue();
	
	double striving = node.get("personality").get(1).get("children").get(0).get("percentile").doubleValue();
	double cautiousness = node.get("personality").get(1).get("children").get(1).get("percentile").doubleValue();
	double dutifulness = node.get("personality").get(1).get("children").get(2).get("percentile").doubleValue();
	double orderliness = node.get("personality").get(1).get("children").get(3).get("percentile").doubleValue();
	double discipline = node.get("personality").get(1).get("children").get(4).get("percentile").doubleValue();
	double efficacy = node.get("personality").get(1).get("children").get(5).get("percentile").doubleValue();
	
	double activity = node.get("personality").get(2).get("children").get(0).get("percentile").doubleValue();
	double assertiveness = node.get("personality").get(2).get("children").get(1).get("percentile").doubleValue();
	double cheerfulness = node.get("personality").get(2).get("children").get(2).get("percentile").doubleValue();
	double seeking = node.get("personality").get(2).get("children").get(3).get("percentile").doubleValue();
	double outgoing = node.get("personality").get(2).get("children").get(4).get("percentile").doubleValue();
	double gregariousness = node.get("personality").get(2).get("children").get(5).get("percentile").doubleValue();

	double altruism = node.get("personality").get(3).get("children").get(0).get("percentile").doubleValue();
	double cooperation = node.get("personality").get(3).get("children").get(1).get("percentile").doubleValue();
	double modesty = node.get("personality").get(3).get("children").get(2).get("percentile").doubleValue();
	double uncompromising = node.get("personality").get(3).get("children").get(3).get("percentile").doubleValue();
	double sympathy = node.get("personality").get(3).get("children").get(4).get("percentile").doubleValue();
	double trust = node.get("personality").get(3).get("children").get(5).get("percentile").doubleValue();
	
	double fiery = node.get("personality").get(4).get("children").get(0).get("percentile").doubleValue();
	double worry = node.get("personality").get(4).get("children").get(1).get("percentile").doubleValue();
	double melancholy = node.get("personality").get(4).get("children").get(2).get("percentile").doubleValue();
	double immoderation = node.get("personality").get(4).get("children").get(3).get("percentile").doubleValue();
	double consciousness = node.get("personality").get(4).get("children").get(4).get("percentile").doubleValue();
	double susceptible = node.get("personality").get(4).get("children").get(5).get("percentile").doubleValue();
	
	System.out.println("Openness(知的好奇心)属性");
    System.out.println("Adventurousness(大胆性), Artistic interests(芸術的関心度), Emotionality(情動性), Imagination(想像力), Intellect(思考力), Authority-challenging(現状打破)");
    System.out.println("Conscientiousness(誠実性)属性");
    System.out.println("Achievement striving(達成努力), Cautiousness(注意深さ), Dutifulness(忠実さ), Orderliness(秩序性), Self-discipline(自制力), Self-efficacy(自己効力感)");
    System.out.println("Extraversion(外向性)属性");
    System.out.println("Activity level(活発度), Assertiveness(自己主張), Cheerfulness(明朗性), Excitement-seeking(刺激希求性), Outgoing(友好性), Gregariousness(社交性)");
    System.out.println("Agreeableness(協調性)属性");
    System.out.println("Altruism(利他主義), Cooperation(協働性), Modesty(謙虚さ), Uncompromising(強硬さ), Sympathy(共感度), Trust(信用度)");
    System.out.println("Neuroticism(感情起伏)属性");
    System.out.println("Fiery(激情的), Prone to worry(心配性), Melancholy(悲観的), Immoderation(利己的), Self-consciousness(自意識過剰), Susceptible to stress(低ストレス耐性)");
    
    MySQL mysql = new MySQL();
	mysql.updateImage(openness,  conscientiousness,  extraversion,  agreeableness,  neuroticism,  adventurousness,  artistic, emotionality, 
            imagination,  intellect , challenging,  striving,  cautiousness,  dutifulness,  orderliness,  discipline,  efficacy,
            activity,  assertiveness,  cheerfulness,  seeking,  outgoing,  gregariousness,  altruism,  cooperation,  modesty, 
            uncompromising,  sympathy,  trust,  fiery,  worry,  melancholy,  immoderation,  consciousness,  susceptible, text);
 }		
}