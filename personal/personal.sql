-- MySQL dump 10.13  Distrib 5.5.39, for Win64 (x86)
--
-- Host: localhost    Database: personal
-- ------------------------------------------------------
-- Server version	5.5.39

USE personal;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_album`
--

DROP TABLE IF EXISTS `t_album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_album` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_album`
--

LOCK TABLES `t_album` WRITE;
/*!40000 ALTER TABLE `t_album` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_diary`
--

DROP TABLE IF EXISTS `t_diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_diary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(10000) DEFAULT NULL,
  `creatTime` datetime DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `plainText` varchar(10000) DEFAULT NULL,
  `weather` varchar(255) DEFAULT NULL,
  `week` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_diary`
--

LOCK TABLES `t_diary` WRITE;
/*!40000 ALTER TABLE `t_diary` DISABLE KEYS */;
INSERT INTO `t_diary` VALUES (1,'<p>最近感觉自己的状态很不对，感觉是自己的心里的事情太多了，没有地方发泄，压得自己已经喘不过气来了。</p><p>喜欢上一个人，就对她有很强的占有欲，我不知道这个对不对。总是不想让她和除了自己的其他的男人聊天。感觉一分钟没有她的声音，心里就是空落落的。每天都尽力让自己不去想，想让忙碌把自己填满，但是最终都以失败告终。突然发现对其置之不理好难。。好难。。</p><p style=\"text-align: center;\"><img src=\"/common/get/f7222c06da06932ee3f523eff503163b.jpg\"/></p><p>新进的公司和理想的中的差距有点大，心里生出了失望的感觉。想辞职，但是又不知道如何开口。不想让家里的父母担心，但是却不知道该如何隐瞒，只有自己默默的在心里承受着。</p><p>感觉自己心里的东西已经压得自己喘不过气来了，但是却连倾诉的人都找不到。想想感觉挺悲哀的。不仅影响自己的心情，工作效率也提不起来。唯有将一切的一切默默记录在某个地方。让自己减负。</p><p>希望自己能够尽快的从阴影中走出来吧。。。。</p><p>哎，好没自信。</p>','2016-04-12 15:30:24','1431532800000','最近感觉自己的状态很不对，感觉是自己的心里的事情太多了，没有地方发泄，压得自己已经喘不过气来了。\r 喜欢上一个人，就对她有很强的占有欲，我不知道这个对不对。总是不想让她和除了自己的其他的男人聊天。感觉一分钟没有她的声音，心里就是空落落的。每天都尽力让自己不去想，想让忙碌把自己填满，但是最终都以失败告终。突然发现对其置之不理好难。。好难。。\r <img src=\"\"/common/get/f7222c06da06932ee3f523eff503163b.jpg\"\" _src=\"\"/common/get/f7222c06da06932ee3f523eff503163b.jpg\"\">\r 新进的公司和理想的中的差距有点大，心里生出了失望的感觉。想辞职，但是又不知道如何开口。不想让家里的父母担心，但是却不知道该如何隐瞒，只有自己默默的在心里承受着。\r 感觉自己心里的东西已经压得自己喘不过气来了，但是却连倾诉的人都找不到。想想感觉挺悲哀的。不仅影响自己的心情，工作效率也提不起来。唯有将一切的一切默默记录在某个地方。让自己减负。\r 希望自己能够尽快的从阴影中走出来吧。。。。\r 哎，好没自信。\r ','阴天','星期四'),(2,'<p style=\"white-space: normal;\">&nbsp; &nbsp; &nbsp; &nbsp; 今天是我第一次写日记，但是我没有想到会是这样的心情。。。</p><p style=\"white-space: normal;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 今天是一个特殊的日子，我又再一次向她表白了，但是，结果还是杳无音讯，我不知道这个词语是否合适，但是我直到第二天也没有得到回复。虽然她之前也拒绝过我，但是我就是不死心，我也不知道我为什么会这么的执着，但是我的心里真的放不下，我真的很喜欢她。。。。</p><p style=\"white-space: normal; text-align: center;\"><img src=\"/common/get/a7a1ed7856d66f4315b392668de34ac8.jpg\"/></p><p style=\"white-space: normal;\">&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;我不知道我能在这个飘渺的爱情中走多久，坚持多久。我现在身边有一份很近的幸福，但是，我不敢接受，这个对于对方来说不公平，因为我现在的心里还藏着另外一个人，我怕我会伤害到对方。</p><p style=\"white-space: normal;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 是应该继续在这份看不到黎明的感情世界里坚持着？还是该放下这一切，去追求一份近在眼前的幸福。。。。。</p><p><br/></p>','2016-04-12 15:30:24','1400774400000','        今天是我第一次写日记，但是我没有想到会是这样的心情。。。\r         今天是一个特殊的日子，我又再一次向她表白了，但是，结果还是杳无音讯，我不知道这个词语是否合适，但是我直到第二天也没有得到回复。虽然她之前也拒绝过我，但是我就是不死心，我也不知道我为什么会这么的执着，但是我的心里真的放不下，我真的很喜欢她。。。。\r <img src=\"\"/common/get/a7a1ed7856d66f4315b392668de34ac8.jpg\"\" _src=\"\"/common/get/a7a1ed7856d66f4315b392668de34ac8.jpg\"\">\r         我不知道我能在这个飘渺的爱情中走多久，坚持多久。我现在身边有一份很近的幸福，但是，我不敢接受，这个对于对方来说不公平，因为我现在的心里还藏着另外一个人，我怕我会伤害到对方。\r         是应该继续在这份看不到黎明的感情世界里坚持着？还是该放下这一切，去追求一份近在眼前的幸福。。。。。\r \r ','天气晴','星期五'),(3,'<p style=\"white-space: normal;\">&nbsp; &nbsp; &nbsp;今天算是彻底的放弃了吗？我也不知道。再怎么说也努力了好久了，说放弃就放弃也不是那么容易的事情。</p><p style=\"white-space: normal;\">&nbsp; &nbsp; 算了吧。既然没有缘分，再怎么努力也都是白费力气。相信以后能够还能够做朋友。</p><p style=\"white-space: normal;\">&nbsp; &nbsp; 一个人的日子已经够了，好想能够有一个人在一起说说话，一起出去旅游，一起做任何想做的事情。每次到外面看着成双成对的情侣们，心里好生羡慕。</p><p style=\"white-space: normal;\">&nbsp; &nbsp; 亲爱的你，你什么时候才能来到我的身边。。。。。。</p><p style=\"white-space: normal; text-align: center;\"><img src=\"/common/get/af48980d2eb3de1f34ba587fa9f81d29.jpg\"/></p><p><br/></p>','2016-04-12 15:30:24','1428076800000','     今天算是彻底的放弃了吗？我也不知道。再怎么说也努力了好久了，说放弃就放弃也不是那么容易的事情。\r     算了吧。既然没有缘分，再怎么努力也都是白费力气。相信以后能够还能够做朋友。\r     一个人的日子已经够了，好想能够有一个人在一起说说话，一起出去旅游，一起做任何想做的事情。每次到外面看着成双成对的情侣们，心里好生羡慕。\r     亲爱的你，你什么时候才能来到我的身边。。。。。。\r <img src=\"\"/common/get/af48980d2eb3de1f34ba587fa9f81d29.jpg\"\" _src=\"\"/common/get/af48980d2eb3de1f34ba587fa9f81d29.jpg\"\">\r \r ','阴天','星期六'),(4,'<p style=\"white-space: normal;\">&nbsp; &nbsp; 最近几天看到朋友圈里出现了各种各样的毕业照，才知道又是一年一度的毕业季已经到来，而我自己也已经毕业一年了，顿时感慨万千。</p><p style=\"white-space: normal;\">&nbsp; &nbsp; 去年的这个时候，我也正在经历着告别，告别自己的同学，朋友，告别自己的读书生涯。转眼一年的时间过去了，但是一切仿佛就在昨天，自己周围的同学，朋友，正在诉说着离别的伤感，诉说着内心深处的声音。。。。心里还是有那么一点小小的伤感。</p><p style=\"white-space: normal;\">&nbsp; &nbsp; 转眼，一年已然消逝。曾经的自己，对大学围墙以外的世界充满了好奇，一切尽在掌握之中的豪情壮志。出来之后才发现，理想很丰满，现实却很骨感。</p><p style=\"white-space: normal;\"><img src=\"/common/get/3346c5a568fc2ef718fdbc202bf9bca7.jpg\"/></p><p style=\"white-space: normal;\">&nbsp; &nbsp; 我不知道我在这一年之中到底达到了一个什么高度。或许在同学，朋友眼中，我还是算比较成功的。但是我却对我自己的表现并不满意。或许是过不去心里的那道坎，或许是对自己的期望太高。</p><p style=\"white-space: normal;\">&nbsp; &nbsp; 从小出生在一个比较贫穷的家庭，童年的黯淡无光，已经让我对贫穷产生了深深的恐惧感。我害怕贫穷，从内心深处的害怕。所以，我立志长大了以后一定要让自己摆脱贫困，让父母摆脱贫困，让这个家庭摆脱贫困。因此，我对自己的期望很高。父母依然。</p><p style=\"white-space: normal;\">&nbsp; &nbsp; 不管现在的自己处于一个什么样的高度。我还是要一步一步的走。哪怕自己已经遍体鳞伤，也要忍住泪水，勇敢向前。光明，就在前方。未来，是属于我们的。</p><p><br/></p>','2016-04-12 15:30:24','1403193600000','    最近几天看到朋友圈里出现了各种各样的毕业照，才知道又是一年一度的毕业季已经到来，而我自己也已经毕业一年了，顿时感慨万千。\r     去年的这个时候，我也正在经历着告别，告别自己的同学，朋友，告别自己的读书生涯。转眼一年的时间过去了，但是一切仿佛就在昨天，自己周围的同学，朋友，正在诉说着离别的伤感，诉说着内心深处的声音。。。。心里还是有那么一点小小的伤感。\r     转眼，一年已然消逝。曾经的自己，对大学围墙以外的世界充满了好奇，一切尽在掌握之中的豪情壮志。出来之后才发现，理想很丰满，现实却很骨感。\r <img src=\"\"/common/get/3346c5a568fc2ef718fdbc202bf9bca7.jpg\"\" _src=\"\"/common/get/3346c5a568fc2ef718fdbc202bf9bca7.jpg\"\">\r     我不知道我在这一年之中到底达到了一个什么高度。或许在同学，朋友眼中，我还是算比较成功的。但是我却对我自己的表现并不满意。或许是过不去心里的那道坎，或许是对自己的期望太高。\r     从小出生在一个比较贫穷的家庭，童年的黯淡无光，已经让我对贫穷产生了深深的恐惧感。我害怕贫穷，从内心深处的害怕。所以，我立志长大了以后一定要让自己摆脱贫困，让父母摆脱贫困，让这个家庭摆脱贫困。因此，我对自己的期望很高。父母依然。\r     不管现在的自己处于一个什么样的高度。我还是要一步一步的走。哪怕自己已经遍体鳞伤，也要忍住泪水，勇敢向前。光明，就在前方。未来，是属于我们的。\r \r ','阴天','星期五'),(5,'<p style=\"text-align: center;\">其实我的要求并不多</p><p style=\"text-align: center;\">只是希望</p><p style=\"text-align: center;\">能得到一个爱的回应</p><p style=\"text-align: center;\">但是</p><p style=\"text-align: center;\">这个看似简单的愿望</p><p style=\"text-align: center;\">似乎</p><p style=\"text-align: center;\">是那么的遥不可及</p><p style=\"text-align: center;\">你</p><p style=\"text-align: center;\">是否能听到</p><p style=\"text-align: center;\">我内心深处的呼喊</p><p style=\"text-align: center;\"><img src=\"/common/get/8c4f037fee94e37a3e8615f16258b50e.jpg\"/></p>','2016-04-12 15:30:24','1433088000000','其实我的要求并不多\r 只是希望\r 能得到一个爱的回应\r 但是\r 这个看似简单的愿望\r 似乎\r 是那么的遥不可及\r 你\r 是否能听到\r 我内心深处的呼喊\r <img src=\"\"/common/get/8c4f037fee94e37a3e8615f16258b50e.jpg\"\" _src=\"\"/common/get/8c4f037fee94e37a3e8615f16258b50e.jpg\"\">\r ','晴','星期一'),(6,'<p style=\"text-align: center;\">一个月了</p><p style=\"text-align: center;\">却还不见归期</p><p style=\"text-align: center;\">月亮</p><p style=\"text-align: center;\">已然不能承载</p><p style=\"text-align: center;\">我对你</p><p style=\"text-align: center;\">深深的思念</p><p style=\"text-align: center;\">看见</p><p style=\"text-align: center;\">你手上的一道道伤疤</p><p style=\"text-align: center;\">心如刀割</p><p style=\"text-align: center;\">假如我能为你分担</p><p style=\"text-align: center;\">纵然十倍于你</p><p style=\"text-align: center;\">我亦愿意</p><p style=\"text-align: center;\"><img src=\"/common/get/83ad816fcd42b49e050662065a86ef65.jpg\"/></p><p style=\"text-align: center;\">好恨我自己</p><p style=\"text-align: center;\">不能在你最需要我的时候</p><p style=\"text-align: center;\">陪在你的身边</p><p style=\"text-align: center;\">我唯一能为你做的</p><p style=\"text-align: center;\">就是</p><p style=\"text-align: center;\">心里默默的祝福</p><p><br/></p><p style=\"text-align: center;\">你若安好</p><p style=\"text-align: center;\">便是晴天</p>','2016-04-12 15:30:24','1433088000000','一个月了\r 却还不见归期\r 月亮\r 已然不能承载\r 我对你\r 深深的思念\r 看见\r 你手上的一道道伤疤\r 心如刀割\r 假如我能为你分担\r 纵然十倍于你\r 我亦愿意\r <img src=\"\"/common/get/83ad816fcd42b49e050662065a86ef65.jpg\"\" _src=\"\"/common/get/83ad816fcd42b49e050662065a86ef65.jpg\"\">\r 好恨我自己\r 不能在你最需要我的时候\r 陪在你的身边\r 我唯一能为你做的\r 就是\r 心里默默的祝福\r \r 你若安好\r 便是晴天\r ','晴','星期一'),(7,'<p>&nbsp;&nbsp;&nbsp;&nbsp;周六的天气，艳阳高照，晴空万里。但是我的心，却阴雨绵绵。<br/></p><p><br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;不知道从什么时候开始，你的言谈举止，你的一颦一笑，都牵动着我的每一寸神经。你笑，我愿意和你一起笑。你哭，我亦愿意陪你一起流泪。你不知道，当你的心里开始阴雨绵绵的时候，我这边已然是狂风暴雨了。<br/></p><p><br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;有时候觉得自己真的好失败，一直都只在你的心门外徘徊，从未真正的走进过你的内心。以至于在你极度伤心郁闷的时候，我却束手无策。</p><p><br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;其实，我对你的要求很简单，我只想让你每天都开心。。。因为只有你开心了，我的世界才能有阳光。<br/></p><p><br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;你若安好，便是晴天。。。。<br/></p>','2016-04-12 15:30:24','1433520000000','    周六的天气，艳阳高照，晴空万里。但是我的心，却阴雨绵绵。\r \r     不知道从什么时候开始，你的言谈举止，你的一颦一笑，都牵动着我的每一寸神经。你笑，我愿意和你一起笑。你哭，我亦愿意陪你一起流泪。你不知道，当你的心里开始阴雨绵绵的时候，我这边已然是狂风暴雨了。\r \r     有时候觉得自己真的好失败，一直都只在你的心门外徘徊，从未真正的走进过你的内心。以至于在你极度伤心郁闷的时候，我却束手无策。\r \r     其实，我对你的要求很简单，我只想让你每天都开心。。。因为只有你开心了，我的世界才能有阳光。\r \r     你若安好，便是晴天。。。。\r ','晴','星期六'),(8,'<p>&nbsp;&nbsp;&nbsp;&nbsp;今天早上看到好友写了一段话，好喜欢，摘抄下来，当做自己的人生格言了。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;<br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;<br/></p><p>&nbsp;&nbsp;&nbsp;&nbsp;</p><p>&nbsp;&nbsp;&nbsp;&nbsp;放下背负，减轻心压，不让忧伤成为单曲循环！活着就得向前看，不必在苦闷中沉沦！要活就要活得精彩！要活就要活得漂亮！过去已经成为记忆，让今天和明天释放光芒！我们，一定要幸福！</p><p><br/></p><p style=\"text-align: right;\">----文 &nbsp;雨袂独舞</p><p style=\"text-align: right;\"><br/></p><p style=\"text-align: center;\"><img src=\"/common/get/bc5f9439cd0e5bad8122599ab44f6734.jpg\"/>&nbsp;</p><p><br/></p>','2016-04-12 15:30:24','1433865600000','    今天早上看到好友写了一段话，好喜欢，摘抄下来，当做自己的人生格言了。\r     \r     \r     \r     放下背负，减轻心压，不让忧伤成为单曲循环！活着就得向前看，不必在苦闷中沉沦！要活就要活得精彩！要活就要活得漂亮！过去已经成为记忆，让今天和明天释放光芒！我们，一定要幸福！\r \r ----文  雨袂独舞\r \r <img src=\"\"/common/get/bc5f9439cd0e5bad8122599ab44f6734.jpg\"\" _src=\"\"/common/get/bc5f9439cd0e5bad8122599ab44f6734.jpg\"\"> \r \r ','天气阴','星期三'),(9,'<p>心中有千言万语</p><p>却不知道如何落笔</p><p>两个多月的痴痴等待</p><p>换来了悄无声息的离开</p><p>心中有不甘，却也无能为力</p><p>两个月的点点滴滴一幕幕的出现在眼前</p><p>曾经的欢声笑语</p><p>一幕幕的美好憧憬</p><p>感觉所有的事情仿佛就在昨天</p><p>该来的总会来</p><p>要走的也留不住</p><p>也许</p><p>这就是老天的安排</p><p>既然我们无力改变</p><p>那就互道再见</p><p>还是想对你说一句</p><p>珍重万千</p><p><br/></p>','2016-04-12 15:30:24','1436371200000','心中有千言万语\r 却不知道如何落笔\r 两个多月的痴痴等待\r 换来了悄无声息的离开\r 心中有不甘，却也无能为力\r 两个月的点点滴滴一幕幕的出现在眼前\r 曾经的欢声笑语\r 一幕幕的美好憧憬\r 感觉所有的事情仿佛就在昨天\r 该来的总会来\r 要走的也留不住\r 也许\r 这就是老天的安排\r 既然我们无力改变\r 那就互道再见\r 还是想对你说一句\r 珍重万千\r \r ','天气晴','星期四'),(10,'<p>感觉心里空落落的。做事情也完全提不起精神。每天活得像个行尸走肉一般。</p><p>也许是两个月的聊天已然成为了一种习惯了吧。突然一下子安静了下来，感觉好不适应。</p><p>也许，我真的应该学会一个人的生活。</p><p>一个人回家</p><p>一个人做饭</p><p>一个人吃</p><p>一个人的旅行</p><p>。。。。</p><p><br/></p>','2016-04-12 15:30:24','1436457600000','感觉心里空落落的。做事情也完全提不起精神。每天活得像个行尸走肉一般。\r 也许是两个月的聊天已然成为了一种习惯了吧。突然一下子安静了下来，感觉好不适应。\r 也许，我真的应该学会一个人的生活。\r 一个人回家\r 一个人做饭\r 一个人吃\r 一个人的旅行\r 。。。。\r \r ','天气晴','星期五'),(11,'<p>&nbsp;&nbsp;&nbsp;&nbsp;今天算是和她正式相处的第一天，本来以为会很开心，但是现实似乎和理想存在着一些差距。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;晚上和她的弟弟及表弟等人一起逛街，除了她以外，其他的差不多都是陌生人，也没有聊天。就一直跟在后面而已。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;到了超市，推了一个购物车，同样是跟在众人后面，但是我没有想到的是，我在这群人里面扮演的就是一个不折不扣的推车人。前面一大堆人有说有笑的走着，我就推着一个车在后面跟着。我努力的哼着歌来平复我心里的孤独与不快。但是我的心骗不了我自己，我就是很不高兴。。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;我不希望其他人怎么样怎么样，但是我内心真的好希望她能够回头看我一眼，问我一下，能够读懂我当时心中的不快与孤独。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;然而，她没有。。。。。。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;那一刻，心好痛。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;这个难道就是我苦等了这么久而换来的爱情。。。。。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;为什么理想和现实的差距会这么大。。。。。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;为什么。。。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;为什么。。。</p><p><br/></p>','2016-04-12 15:30:24','1438272000000','    今天算是和她正式相处的第一天，本来以为会很开心，但是现实似乎和理想存在着一些差距。\r     晚上和她的弟弟及表弟等人一起逛街，除了她以外，其他的差不多都是陌生人，也没有聊天。就一直跟在后面而已。\r     到了超市，推了一个购物车，同样是跟在众人后面，但是我没有想到的是，我在这群人里面扮演的就是一个不折不扣的推车人。前面一大堆人有说有笑的走着，我就推着一个车在后面跟着。我努力的哼着歌来平复我心里的孤独与不快。但是我的心骗不了我自己，我就是很不高兴。。\r     我不希望其他人怎么样怎么样，但是我内心真的好希望她能够回头看我一眼，问我一下，能够读懂我当时心中的不快与孤独。\r     然而，她没有。。。。。。\r     那一刻，心好痛。\r     这个难道就是我苦等了这么久而换来的爱情。。。。。\r     为什么理想和现实的差距会这么大。。。。。\r     为什么。。。\r     为什么。。。\r \r ','天气晴','星期五'),(12,'<p>今天是我和她在一起的第一个七夕节！</p><p>我是不是应该为她的坦诚感到高兴呢？</p><p>但是真实的情况是，我的心里其实一点都不高兴。尤其是当知道了她和一个男人之前单独的出去了，最重要的是那个男人还是她的前男友。</p><p>我相信她不会做对不起我的事情，但是我的心里就是不高兴。</p><p>我不知道应该怎么来定义和前任的关系。从心里的真实感受来说，我是真的不想他们两个见面，尤其是单独见面。</p><p>我该怎么做。。。</p><p>怎么做。。。</p>','2016-04-12 15:30:24','1440000000000','今天是我和她在一起的第一个七夕节！\r 我是不是应该为她的坦诚感到高兴呢？\r 但是真实的情况是，我的心里其实一点都不高兴。尤其是当知道了她和一个男人之前单独的出去了，最重要的是那个男人还是她的前男友。\r 我相信她不会做对不起我的事情，但是我的心里就是不高兴。\r 我不知道应该怎么来定义和前任的关系。从心里的真实感受来说，我是真的不想他们两个见面，尤其是单独见面。\r 我该怎么做。。。\r 怎么做。。。\r ','阴天','星期四'),(13,'<p>又是一个周末</p><p>本来好不容易盼来的周末，心情应该很高兴才对。而事实却是恰好相反。</p><p>我原来并不知道她的脾气是那么的变化无常。说话说重了要生气，说话的语气不对要生气，就连做了错事说了两句也好生气。</p><p>这还是我理想中的她吗？</p><p>我最近常常在反问我自己。</p><p>每天上班就已经很累了，下了班回家还要对她赔笑脸，防止她会生气。。。</p><p>我甚至都已经感到了一丝疲惫。</p><p>反正我的内心的最真实的声音告诉我了，这不是我想要的生活。</p><p>也不是我期望的感情生活。</p><p>我到底该怎么办？</p><p>谁能给我答案？</p>','2016-04-12 15:30:24','1440777600000','又是一个周末\r 本来好不容易盼来的周末，心情应该很高兴才对。而事实却是恰好相反。\r 我原来并不知道她的脾气是那么的变化无常。说话说重了要生气，说话的语气不对要生气，就连做了错事说了两句也好生气。\r 这还是我理想中的她吗？\r 我最近常常在反问我自己。\r 每天上班就已经很累了，下了班回家还要对她赔笑脸，防止她会生气。。。\r 我甚至都已经感到了一丝疲惫。\r 反正我的内心的最真实的声音告诉我了，这不是我想要的生活。\r 也不是我期望的感情生活。\r 我到底该怎么办？\r 谁能给我答案？\r ','晴','星期六'),(14,'<p><em>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<span style=\"font-size: 24px; background-color: rgb(149, 55, 52);\">秋色</span></em></p><p><em><span style=\"font-size: 20px;\">&nbsp;<span style=\"font-size: 20px; background-color: rgb(250, 192, 143);\"> &nbsp;金秋是个收获的季节，坐在阳台上放眼望去觉得一切都好美，闻着淡淡的桂花香，清风拂过脸颊，眯着眼睛享受着这安静的一切，瞬间觉得这世界好宁静，我的生活好安逸好完美，其实不是这样的，这只是一个梦境而已，这样走过了春暖花开的春季，痛苦的跨过了炎热的夏季，本想着可以在这个收获的季节，我也可以满载而归，却没有！</span></span></em><br/></p><p><em><span style=\"font-size: 20px; background-color: rgb(250, 192, 143);\">&nbsp; 如果没有这么多的执念，如果没有这么的讲究，如果不计较的那么多，如果得过且过，我是不是会过的很好很好，人总是喜欢活在过去，想念两年前的潇洒，想念的单纯，想念的开心，那时候没有什么事情可以能左右我的心情，一切都那么的开心！人，这样活着好累好累，总是这样颓废的过着每一天，没有了目标，没有了计划，我想这样抛下一切的远走高飞，把心找回来，可现实却阻碍了我</span><img src=\"http://img.baidu.com/hi/face/i_f34.gif\"/></em></p><p><em><span style=\"font-size: 20px;\"></span></em></p>','2016-04-12 15:30:24','1442505600000','                             秋色\r    金秋是个收获的季节，坐在阳台上放眼望去觉得一切都好美，闻着淡淡的桂花香，清风拂过脸颊，眯着眼睛享受着这安静的一切，瞬间觉得这世界好宁静，我的生活好安逸好完美，其实不是这样的，这只是一个梦境而已，这样走过了春暖花开的春季，痛苦的跨过了炎热的夏季，本想着可以在这个收获的季节，我也可以满载而归，却没有！\r   如果没有这么多的执念，如果没有这么的讲究，如果不计较的那么多，如果得过且过，我是不是会过的很好很好，人总是喜欢活在过去，想念两年前的潇洒，想念的单纯，想念的开心，那时候没有什么事情可以能左右我的心情，一切都那么的开心！人，这样活着好累好累，总是这样颓废的过着每一天，没有了目标，没有了计划，我想这样抛下一切的远走高飞，把心找回来，可现实却阻碍了我<img src=\"\"http://img.baidu.com/hi/face/i_f34.gif\"\" _src=\"\"http://img.baidu.com/hi/face/i_f34.gif\"\">\r \r ','雨','星期五'),(15,'<p>今天感觉做什么事情都提不起精神来，想好好的休息却睡不着。整个人感觉静不下心来，很浮躁。</p><p>哎，注定是一个难熬的一天。</p><p>还是希望这种情绪能够早点消失，早日回归正常。</p><p><img src=\"/common/get/add1e4de63d0316c531eabfa32e42df5.jpg\"/></p><p><br/></p>','2016-04-12 15:30:24','1447603200000','今天感觉做什么事情都提不起精神来，想好好的休息却睡不着。整个人感觉静不下心来，很浮躁。\r 哎，注定是一个难熬的一天。\r 还是希望这种情绪能够早点消失，早日回归正常。\r <img src=\"\"/common/get/add1e4de63d0316c531eabfa32e42df5.jpg\"\" _src=\"\"/common/get/add1e4de63d0316c531eabfa32e42df5.jpg\"\">\r \r ','阴天','星期一');
/*!40000 ALTER TABLE `t_diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_english`
--

DROP TABLE IF EXISTS `t_english`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_english` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mp3Url` varchar(255) DEFAULT NULL,
  `orginalName` varchar(255) DEFAULT NULL,
  `text` text,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_english`
--

LOCK TABLES `t_english` WRITE;
/*!40000 ALTER TABLE `t_english` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_english` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_mood`
--

DROP TABLE IF EXISTS `t_mood`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_mood` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_mood`
--

LOCK TABLES `t_mood` WRITE;
/*!40000 ALTER TABLE `t_mood` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_mood` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_mood_background`
--

DROP TABLE IF EXISTS `t_mood_background`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_mood_background` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `imageUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_mood_background`
--

LOCK TABLES `t_mood_background` WRITE;
/*!40000 ALTER TABLE `t_mood_background` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_mood_background` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_photos`
--

DROP TABLE IF EXISTS `t_photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_photos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `albumId` bigint(20) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `isCover` int(11) DEFAULT NULL,
  `showInTheMainPage` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_photos`
--

LOCK TABLES `t_photos` WRITE;
/*!40000 ALTER TABLE `t_photos` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_photos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_websitecollection`
--

DROP TABLE IF EXISTS `t_websitecollection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_websitecollection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `webSiteUrl` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_websitecollection`
--

LOCK TABLES `t_websitecollection` WRITE;
/*!40000 ALTER TABLE `t_websitecollection` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_websitecollection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'personal'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-12 17:49:51
