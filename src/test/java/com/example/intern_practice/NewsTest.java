package com.example.intern_practice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.intern_practice.constants.MSG;
import com.example.intern_practice.entity.News;
import com.example.intern_practice.repository.NewsDao;
import com.example.intern_practice.service.ifs.NewsService;
import com.example.intern_practice.vo.NewsRequest;

@SpringBootTest(classes = InternPracticeApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewsTest {

	@Autowired
	private NewsDao newsDao;

	@Autowired
	private NewsService newsService;

	private String news1 = "　【ジャカルタ共同】岸田文雄首相は6日、インドネシアで開かれた東南アジア諸国連合（ASEAN）プラス3（日中韓）首脳会議に出席した。"
			+ "中国の李強首相と韓国の尹錫悦大統領が参加。岸田首相は東京電力福島第1原発処理水の海洋放出は国際基準に合致し安全性に問題はないと説明し、"
			+ "理解を求める見通し。日本の水産物を全面輸入停止した中国に対し、科学的根拠に基づく対応を促す構えだ。\r\n\r\n　日本政府高官は、"
			+ "岸田首相と李氏が首脳会議前に接触したと明らかにした。会話の内容は明らかにしていない。\r\n\r\n　岸田、李両氏が顔を合わせるのは初めて。"
			+ "中国は処理水を「核汚染水」と呼んで放出に反発しており、首脳会議でも李氏が日本批判を展開する可能性がある。\r\n\r\n "
			+ "ASEANプラス3は、経済や国際情勢などを巡って実務協力を話し合う枠組み。会議の冒頭、岸田首相は「日本は金融、"
			+ "食料安全保障などさまざまな分野で協力を推進している。ASEANプラス3の枠組みで各国との連携をさらに深める」と述べた。";

	private String news2 = "　自民党の麻生太郎副総裁は6日、東京都内で講演し、高水準の賃上げが実現した今年の春闘に関し、岸田政権と連合による「共闘」成果だとの認識を示した。"
			+ "「連合の芳野友子会長が自民党と一緒に取り組んだ結果、3.58％まで上がった」と述べた。自民内には連合が推す国民民主党との連立論がくすぶっている。\r\n"
			+ "\r\n　春闘では、連合が5％程度の賃上げ要求を掲げ、政府も経済界に大幅な賃上げを要請、29年ぶりに3％台を達成した。"
			+ "麻生氏は「一つの流れとして、変化が出てきている」と手応えを強調した。物価上昇に合わせ、給料も引き上げるべきだとし" + "「従業員の幸せを考えないと長期的に経営は成り立たない」とも語った。";

	private String news3 = "秋篠宮悠仁さま誕生\r\n" + "2006（平成18）年　秋篠宮妃紀子さまが、長男悠仁さまを出産された。皇族での男子誕生は秋篠宮さま以来41年ぶり。\r\n" + "\r\n"
			+ "関連記事\r\n" + "「愛子さまを皇太子に」と訴えるイベント開催：女性天皇への道は開けるか\r\n" + "皇位継承はどうなるか（前編）：「未来の天皇」悠仁さままでの流れを前提とした有識者会議案\r\n"
			+ "眞子さまの皇籍離脱で皇室は17人に : 次世代の皇位継承者は悠仁さまお一人\r\n" + "歴代の女性天皇 : 過去の10代8人はいずれも父方に天皇の血筋を引く“男系”\r\n"
			+ "シリーズ・2回のお代替わりを見つめて（12）皇嗣秋篠宮さま：令和の皇室を支える弟宮\r\n" + "その他の出来事\r\n" + "黒澤と溝口、銀獅子賞ダブル受賞\r\n"
			+ "1954（昭和29）年　黒澤明監督『七人の侍』と溝口健二監督『山椒太夫』が、ベネチア国際映画祭で銀獅子賞を受賞。黒澤は98年のこの日、88歳で死去した。\r\n" + "\r\n" + "関連記事\r\n"
			+ "黒澤明：世界の映画界に多大なる影響を与えた巨匠\r\n" + "日本映画界は変われるか：ジェンダー格差、頻発するハラスメント、契約書もない制作現場の実態\r\n"
			+ "「ドライブ・マイ・カー」が国際長編映画賞 : アカデミー賞を受賞した日本作品\r\n" + "海外の映画ポスターが物語る「世界のクロサワ」\r\n" + "「世界のミフネ」と呼ばれた男―三船敏郎\r\n"
			+ "憲政史上初の女性党首誕生\r\n" + "1986（昭和61）年　社会党委員長選で土井たか子副委員長が選出され、日本初の女性党首となった。\r\n" + "\r\n" + "関連記事\r\n"
			+ "参院選の女性当選者、過去最多35人 : 立憲、共産は半数が女性\r\n" + "衆院、女性比率10%割れ : 形骸化する共同参画法、かすむ “ジェンダー平等”\r\n"
			+ "女性政治家増えるべき、でも、家庭との両立難しい : 日本財団・女性意識調査\r\n" + "野田聖子：「男社会」の自民党で女性の政治参加を推進、総裁ポストにこだわる訳\r\n"
			+ "女性議員「後進国」日本－「日本版パリテ法」で何が変わるのか\r\n" + "2014年に逝った人たち\r\n" + "『HANA-BI』が金獅子賞\r\n"
			+ "1997（平成9）年　北野武監督の『HANA-BI』がベネチア国際映画祭で金獅子賞を受賞。日本映画の金獅子賞受賞は『無法松の一生』以来、39年ぶりだった。\r\n" + "\r\n" + "関連記事\r\n"
			+ "「大衆」と「芸術」の頂点に立つ男、北野武の功績とその「仕事スタイル」\r\n" + "北海道で震度7の地震\r\n"
			+ "2018（平成30）年　北海道の胆振地方中東部を震源とする地震が発生、北海道厚真町で震度7の揺れを観測した。土砂崩れや家屋倒壊で40人が死亡。一時は道内の全約295万戸が停電した。\r\n" + "\r\n"
			+ "関連記事\r\n" + "北海道で震度7の地震：過去には道南東側で多く発生\r\n" + "東日本大震災以降の大地震一覧\r\n" + "天皇ご一家、皇居御所に引っ越し\r\n"
			+ "2021（令和3）年　天皇ご一家は、赤坂御用地内の赤坂御所（東京都港区）から皇居・御所（旧吹上仙洞御所）に引っ越された。";

	private String news4 = "関連記事\r\n" + "「愛子さまを皇太子に」と訴えるイベント開催：女性天皇への道は開けるか\r\n"
			+ "皇位継承はどうなるか（前編）：「未来の天皇」悠仁さままでの流れを前提とした有識者会議案\r\n" + "眞子さまの皇籍離脱で皇室は17人に : 次世代の皇位継承者は悠仁さまお一人\r\n"
			+ "歴代の女性天皇 : 過去の10代8人はいずれも父方に天皇の血筋を引く“男系”\r\n" + "シリーズ・2回のお代替わりを見つめて（12）皇嗣秋篠宮さま：令和の皇室を支える弟宮\r\n"
			+ "その他の出来事\r\n" + "黒澤と溝口、銀獅子賞ダブル受賞\r\n"
			+ "1954（昭和29）年　黒澤明監督『七人の侍』と溝口健二監督『山椒太夫』が、ベネチア国際映画祭で銀獅子賞を受賞。黒澤は98年のこの日、88歳で死去した。\r\n" + "\r\n" + "関連記事\r\n"
			+ "黒澤明：世界の映画界に多大なる影響を与えた巨匠\r\n" + "日本映画界は変われるか：ジェンダー格差、頻発するハラスメント、契約書もない制作現場の実態\r\n"
			+ "「ドライブ・マイ・カー」が国際長編映画賞 : アカデミー賞を受賞した日本作品\r\n" + "海外の映画ポスターが物語る「世界のクロサワ」\r\n" + "「世界のミフネ」と呼ばれた男―三船敏郎\r\n"
			+ "憲政史上初の女性党首誕生\r\n" + "1986（昭和61）年　社会党委員長選で土井たか子副委員長が選出され、日本初の女性党首となった。\r\n" + "\r\n" + "関連記事\r\n"
			+ "参院選の女性当選者、過去最多35人 : 立憲、共産は半数が女性\r\n" + "衆院、女性比率10%割れ : 形骸化する共同参画法、かすむ “ジェンダー平等”\r\n"
			+ "女性政治家増えるべき、でも、家庭との両立難しい : 日本財団・女性意識調査\r\n" + "野田聖子：「男社会」の自民党で女性の政治参加を推進、総裁ポストにこだわる訳\r\n"
			+ "女性議員「後進国」日本－「日本版パリテ法」で何が変わるのか\r\n" + "2014年に逝った人たち\r\n" + "『HANA-BI』が金獅子賞\r\n"
			+ "1997（平成9）年　北野武監督の『HANA-BI』がベネチア国際映画祭で金獅子賞を受賞。日本映画の金獅子賞受賞は『無法松の一生』以来、39年ぶりだった。\r\n" + "\r\n" + "関連記事\r\n"
			+ "「大衆」と「芸術」の頂点に立つ男、北野武の功績とその「仕事スタイル」\r\n" + "北海道で震度7の地震\r\n"
			+ "2018（平成30）年　北海道の胆振地方中東部を震源とする地震が発生、北海道厚真町で震度7の揺れを観測した。土砂崩れや家屋倒壊で40人が死亡。一時は道内の全約295万戸が停電した。";

	@BeforeAll
	private void BeforeAll() {
		newsDao.save(new News(1, "トップ", "政治", "首相、処理水の安全性説明へ　中国に科学的対応要求",
				"【ジャカルタ共同】岸田文雄首相は6日、インドネシアで開かれた東南アジア諸国連合（ASEAN）プラス3", "主要,政治", news1,
				LocalDateTime.of(2023, 9, 6, 11, 28), LocalDateTime.of(2023, 9, 6, 17, 11), null,
				LocalDateTime.of(2023, 9, 9, 00, 00), null, "© 一般社団法人共同通信社", null, null, 0, 0, 0, 1, 1, false));
	}

	@Test
	public void insertNewsTest() {
		Assert.isTrue(
				newsDao.insertNews("トップ", "政治", "麻生氏、連合と「共闘」成果強調　国民党との連立論くすぶる",
						"自民党の麻生太郎副総裁は6日、東京都内で講演し、高水準の賃上げが実現した今年の春闘に関し、岸田政権と...", "政治", news2,
						LocalDateTime.of(2023, 9, 6, 15, 43), LocalDateTime.of(2023, 9, 6, 19, 19),
						LocalDateTime.of(2023, 9, 9, 00, 00), "© 一般社団法人共同通信社", 1, 1) == 1,
				MSG.TEST1_ERROR.getMessage());
	}

	@Test
	public void updateNewsTest() {
		Assert.isTrue(
				newsDao.updateNews(0, "トップ", "政治", "麻生氏、連合と「共闘」成果強調　国民党との連立論くすぶる",
						"自民党の麻生太郎副総裁は6日、東京都内で講演し、高水準の賃上げが実現した今年の春闘に関し、岸田政権と...", "政治", news2,
						LocalDateTime.of(2023, 9, 6, 19, 27), LocalDateTime.of(2023, 9, 6, 19, 21),
						LocalDateTime.of(2023, 9, 9, 00, 00), "© 一般社団法人共同通信社", 1, 1) == 0,
				MSG.TEST1_ERROR.getMessage());
		Assert.isTrue(
				newsDao.updateNews(2, "トップ", "政治", "麻生氏、連合と「共闘」成果強調　国民党との連立論くすぶる",
						"自民党の麻生太郎副総裁は6日、東京都内で講演し、高水準の賃上げが実現した今年の春闘に関し、岸田政権と...", "政治", news2,
						LocalDateTime.of(2023, 9, 6, 19, 27), LocalDateTime.of(2023, 9, 6, 19, 21),
						LocalDateTime.of(2023, 9, 9, 00, 00), "© 一般社団法人共同通信社", 1, 1) == 1,
				MSG.TEST2_ERROR.getMessage());
	}

	@Test
	public void plusViewTest() {
		Assert.isTrue(newsDao.plusView(0) == 0, MSG.TEST1_ERROR.getMessage());
		Assert.isTrue(newsDao.plusView(1) == 1, MSG.TEST2_ERROR.getMessage());
	}

	@Test
	public void plusLikeTest() {
		Assert.isTrue(newsDao.plusLike(0) == 0, MSG.TEST1_ERROR.getMessage());
		Assert.isTrue(newsDao.plusLike(1) == 1, MSG.TEST2_ERROR.getMessage());
	}

	@Test
	public void plusDislikeTest() {
		Assert.isTrue(newsDao.plusDislike(0) == 0, MSG.TEST1_ERROR.getMessage());
		Assert.isTrue(newsDao.plusDislike(2) == 1, MSG.TEST2_ERROR.getMessage());
	}

	@Test
	public void deleteNewsTest() {
		Assert.isTrue(newsDao.deleteNews(new ArrayList<>(Arrays.asList(0)), LocalDateTime.of(2023, 9, 6, 19, 27),
				"© 一般社団法人共同通信社") == 0, MSG.TEST1_ERROR.getMessage());
		Assert.isTrue(newsDao.deleteNews(new ArrayList<>(Arrays.asList(2)), LocalDateTime.of(2023, 9, 6, 19, 27),
				"© 一般社団法人共同通信社") == 1, MSG.TEST2_ERROR.getMessage());
	}

	@Test
	public void addNewsTest() {
		NewsRequest request = new NewsRequest();
		Assert.isTrue(newsService.addNews(request).getMessage().equals(MSG.CANNOT_EMPTY.getMessage()),
				MSG.TEST1_ERROR.getMessage());
		request.setCatalog("社会");
		request.setSubcatalog("文化");
		request.setTitle("今日は何の日：9月6日");
		request.setSubtitle("秋篠宮悠仁さま誕生");
		request.setTags("皇室,映画,秋篠宮家,黒澤明,北野武／ビートたけし");
		request.setContent(news3);
		request.setPublishTime(LocalDateTime.of(2023, 9, 6, 22, 0));
		request.setExpirationTime(LocalDateTime.of(2023, 9, 20, 23, 59));
		request.setCreator("無名");
		request.setImportance(3);
		request.setAudienceLevel(3);
		Assert.isTrue(newsService.addNews(request).getMessage().equals(MSG.INCORRECT.getMessage()),
				MSG.TEST2_ERROR.getMessage());
		request.setContent(news4);
		Assert.isTrue(newsService.addNews(request).getMessage().equals(MSG.SUCCESS.getMessage()),
				MSG.TEST3_ERROR.getMessage());
	}

	@Test
	public void findNewsTest() {
		NewsRequest request = new NewsRequest();
		Assert.isTrue(newsService.findNews(null).getNewsList().size() == 3, MSG.TEST1_ERROR.getMessage());
		Assert.isTrue(newsService.findNews(request).getNews() == null, MSG.TEST2_ERROR.getMessage());
		request.setNewsId(2);
		Assert.isTrue(newsService.findNews(request).getNews().getDislikes() == 1, MSG.TEST3_ERROR.getMessage());
	}

	@Test
	public void reviseNewsTest() {
		NewsRequest request = new NewsRequest();
		Assert.isTrue(newsService.reviseNews(request).getMessage().equals(MSG.CANNOT_EMPTY.getMessage()),
				MSG.TEST1_ERROR.getMessage());
		request.setNewsId(100);
		request.setCatalog("社会");
		request.setSubcatalog("文化");
		request.setTitle("今日は何の日：9月6日");
		request.setSubtitle("秋篠宮悠仁さま誕生");
		request.setTags("映画,秋篠宮家,黒澤明,北野武／ビートたけし");
		request.setContent(news4);
		request.setPublishTime(LocalDateTime.of(2023, 9, 6, 22, 0));
		request.setExpirationTime(LocalDateTime.of(2023, 9, 20, 23, 59));
		request.setEditor("無名");
		request.setImportance(3);
		request.setAudienceLevel(3);
		Assert.isTrue(newsService.reviseNews(request).getMessage().equals(MSG.INCORRECT.getMessage()),
				MSG.TEST2_ERROR.getMessage());
		request.setNewsId(3);
		Assert.isTrue(newsService.reviseNews(request).getMessage().equals(MSG.SUCCESS.getMessage()),
				MSG.TEST3_ERROR.getMessage());
	}

	@Test
	public void viewNewsTest() {
		NewsRequest request = new NewsRequest();
		Assert.isTrue(newsService.viewNews(request).getMessage().equals(MSG.CANNOT_EMPTY.getMessage()),
				MSG.TEST1_ERROR.getMessage());
		request.setNewsId(200);
		Assert.isTrue(newsService.viewNews(request).getMessage().equals(MSG.INCORRECT.getMessage()),
				MSG.TEST2_ERROR.getMessage());
		request.setNewsId(2);
		Assert.isTrue(newsService.viewNews(request).getMessage().equals(MSG.SUCCESS.getMessage()),
				MSG.TEST3_ERROR.getMessage());
	}

	@Test
	public void likeNewsTest() {
		NewsRequest request = new NewsRequest();
		Assert.isTrue(newsService.likeNews(request).getMessage().equals(MSG.CANNOT_EMPTY.getMessage()),
				MSG.TEST1_ERROR.getMessage());
		request.setNewsId(200);
		Assert.isTrue(newsService.likeNews(request).getMessage().equals(MSG.INCORRECT.getMessage()),
				MSG.TEST2_ERROR.getMessage());
		request.setNewsId(2);
		Assert.isTrue(newsService.likeNews(request).getMessage().equals(MSG.SUCCESS.getMessage()),
				MSG.TEST3_ERROR.getMessage());
	}

	@Test
	public void dislikeNewsTest() {
		NewsRequest request = new NewsRequest();
		Assert.isTrue(newsService.dislikeNews(request).getMessage().equals(MSG.CANNOT_EMPTY.getMessage()),
				MSG.TEST1_ERROR.getMessage());
		request.setNewsId(200);
		Assert.isTrue(newsService.dislikeNews(request).getMessage().equals(MSG.INCORRECT.getMessage()),
				MSG.TEST2_ERROR.getMessage());
		request.setNewsId(2);
		Assert.isTrue(newsService.dislikeNews(request).getMessage().equals(MSG.SUCCESS.getMessage()),
				MSG.TEST3_ERROR.getMessage());
	}

	@Test
	public void deleteNewsImplTest() {
		NewsRequest request = new NewsRequest();
		Assert.isTrue(newsService.deleteNews(request).getMessage().equals(MSG.CANNOT_EMPTY.getMessage()),
				MSG.TEST1_ERROR.getMessage());
		request.setIdList(new ArrayList<>(Arrays.asList(2)));
		request.setRemover("管理者管理者管理者管理者管理者管理者管理者管理者管理者管理者管理者管理者管理者管理者管理者1");
		Assert.isTrue(newsService.deleteNews(request).getMessage().equals(MSG.INCORRECT.getMessage()),
				MSG.TEST2_ERROR.getMessage());
		request.setRemover("管理者1");
		Assert.isTrue(newsService.deleteNews(request).getMessage().equals(MSG.SUCCESS.getMessage()),
				MSG.TEST3_ERROR.getMessage());
	}
}
