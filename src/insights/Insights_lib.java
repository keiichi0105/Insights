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
		
			text = 	"���{�������P�S���A���V�A�̃v�[�`���哝�̂Ƃ̎�]��k�ŁA���\�����錾����b�ɕ��a���̒�������������������j�ň�v�������Ƃ��󂯂āA"
					+ "���E�ł͔g�䂪�L�����Ă��܂��B���{�W�O�񑊂͂P�W����A�V���K�|�[���A�I�[�X�g�����A�A�p�v�A�j���[�M�j�A�̂R�J����K���I���A���{��p�@�ŉH�c��`�ɋA�������B"
					+ "�T���Ԃ̗�K�Łu���R�ŊJ���ꂽ�C���h�����m�v�\�z�⎩�R�f�Ր��i�̈Ӌ`���d�˂Ĕ��M���A�e����]�Ƃ̉�k���ϋɓI�ɂ��Ȃ����B�������A�؍��̕��ݓЁi�����E�W�F�C���j"
					+ "�哝�̂Ƃ̉�k�͂Ȃ������B�����p�H�ɂ��i�ׂŊ؍��ō��ق����{��Ƃɔ����𖽂��������ւ̑Ή��������Ȃ������Ɖ�k���Ă����Ӗ����Ɣ��f�A�u�헪�I���u�v�ɓO�����悤���B";
			
		 options = new ProfileOptions.Builder()
	  .contentLanguage(Language.JA) //���{�����
		 .acceptLanguage(Language.JA)  //���{��o��
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
	
	System.out.println("Openness(�m�I�D��S)����");
    System.out.println("Adventurousness(��_��), Artistic interests(�|�p�I�֐S�x), Emotionality(���), Imagination(�z����), Intellect(�v�l��), Authority-challenging(����Ŕj)");
    System.out.println("Conscientiousness(������)����");
    System.out.println("Achievement striving(�B���w��), Cautiousness(���Ӑ[��), Dutifulness(������), Orderliness(������), Self-discipline(������), Self-efficacy(���Ȍ��͊�)");
    System.out.println("Extraversion(�O����)����");
    System.out.println("Activity level(�����x), Assertiveness(���Ȏ咣), Cheerfulness(���N��), Excitement-seeking(�h���󋁐�), Outgoing(�F�D��), Gregariousness(�Ќ�)");
    System.out.println("Agreeableness(������)����");
    System.out.println("Altruism(������`), Cooperation(������), Modesty(������), Uncompromising(���d��), Sympathy(�����x), Trust(�M�p�x)");
    System.out.println("Neuroticism(����N��)����");
    System.out.println("Fiery(����I), Prone to worry(�S�z��), Melancholy(�ߊϓI), Immoderation(���ȓI), Self-consciousness(���ӎ��ߏ�), Susceptible to stress(��X�g���X�ϐ�)");
    
    MySQL mysql = new MySQL();
	mysql.updateImage(openness,  conscientiousness,  extraversion,  agreeableness,  neuroticism,  adventurousness,  artistic, emotionality, 
            imagination,  intellect , challenging,  striving,  cautiousness,  dutifulness,  orderliness,  discipline,  efficacy,
            activity,  assertiveness,  cheerfulness,  seeking,  outgoing,  gregariousness,  altruism,  cooperation,  modesty, 
            uncompromising,  sympathy,  trust,  fiery,  worry,  melancholy,  immoderation,  consciousness,  susceptible, text);
 }		
}