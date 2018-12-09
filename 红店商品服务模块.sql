-- ----------------------------
--  Table structure for `goods_info_tb`
-- ----------------------------
DROP TABLE IF EXISTS `goods_info_tb`;
CREATE TABLE `goods_info_tb` (
  `id` varchar(200)  not NULL COMMENT '商品表id',
  `goods_code` varchar(50)  not NULL COMMENT '商品编码',
  `price` decimal(20,2) NOT NULL COMMENT '商品价格',
  `cost_price` decimal(20,2)  NULL COMMENT '成本价',
  `sales_promotion` int(10) DEFAULT '0' COMMENT '是否促销,0-未促销,1=促销',
 -- `menu_id` varchar(50) DEFAULT  NULL COMMENT '商品套餐表id',
  `seller` varchar(100) DEFAULT NULL COMMENT '卖家',
  `site` varchar(200) DEFAULT NULL COMMENT '位置',
  `brand` varchar(50) DEFAULT NULL COMMENT '品牌',
  `name` varchar(100) DEFAULT NULL COMMENT '商品名称',
 --  `type_id` int(10) DEFAULT NULL COMMENT '商品类目id',
  `sell_number` varchar(30) NULL COMMENT '卖家手机号',
  `sell_status` int(10) DEFAULT '0' COMMENT '销售状态（10-在销、20-下架、30-销空、40-缺货、0-停售...）',
  `goods_desc` varchar(100) DEFAULT NULL COMMENT '商品描述',
  `tips` varchar(200) DEFAULT NULL COMMENT '提示', 
  `detail` text COMMENT '商品详情',
  `unit` varchar(50) DEFAULT NULL COMMENT '单位',  
  `instruction` varchar(500) DEFAULT NULL COMMENT '使用说明',
  `start_time` datetime DEFAULT NULL COMMENT '开始销售时间（上架时间）',     
  `end_time` datetime DEFAULT NULL COMMENT '结束销售时间（下架时间）',     
  `value` varchar(50) DEFAULT NULL COMMENT '价值（金额），单位：元',    
  `precondition` varchar(200) DEFAULT NULL COMMENT '购买（获取）前提条件',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',   
  PRIMARY KEY (`id`)
   ) COMMENT='商品表' 
 ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;
COMMIT;
-- ----------------------------
--  Table structure for `goods_set_menu_tb`
-- ----------------------------
DROP TABLE IF EXISTS `goods_set_menu_tb`;
CREATE TABLE `goods_set_menu_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `good_set_id` varchar(200) not NULL COMMENT '商品套餐表id',
  `name` varchar(100) DEFAULT NULL COMMENT '套餐名字',
  `type_id` VARCHAR(50) DEFAULT NULL COMMENT '类型id',
  `instruction` varchar(500) DEFAULT NULL COMMENT '使用说明',
  `enable_count` int(10) DEFAULT '0' COMMENT '适用人数',
  `unit` varchar(50) DEFAULT NULL COMMENT '单位',  
  `sell_status` int(10) DEFAULT '0' COMMENT '销售状态（10-在销、20-下架、30-销空、40-缺货、0-停售...）',
  `goods_set_desc` varchar(100) DEFAULT NULL COMMENT '商品套餐描述',
  `sales_price` decimal(20,2) NOT NULL COMMENT '销售价',
  `original_price` decimal(20,2) NOT NULL COMMENT '原价',
  `goods_code` varchar(50) not NULL COMMENT '商品编码',
  `cost_price` decimal(20,2) NOT NULL COMMENT '成本价',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',   
  `start_time` datetime DEFAULT NULL COMMENT '开始销售时间（上架时间）',     
  `end_time` datetime DEFAULT NULL COMMENT '结束销售时间（下架时间）',     
  `precondition` varchar(200) DEFAULT NULL COMMENT '购买（获取）前提条件',
  `sell_count` int(10) DEFAULT '0' COMMENT '销售总数',
  PRIMARY KEY (`id`)
   ) COMMENT='商品套餐表' 
 ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;
COMMIT;

-- -- ----------------------------
-- --  Table structure for `goods_type_info_tb`
-- -- ----------------------------
-- DROP TABLE IF EXISTS `goods_type_info_tb`;
-- CREATE TABLE `goods_type_info_tb` (
--   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品-类别关系信息表id',
--   `goods_id` varchar(50) DEFAULT NULL COMMENT '商品id',
--   'menu_id' varchar(50) DEFAULT NULL COMMENT '商品套餐id',
--   `type_id` int(11) DEFAULT NULL COMMENT '类别id',
--   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
--   `update_time` datetime DEFAULT NULL COMMENT '更新时间',
--   PRIMARY KEY (`id`)
-- ) COMMENT='商品-类别关系信息表' ENGINE=InnoDB AUTO_INCREMENT=100032 DEFAULT CHARSET=utf8;
-- COMMIT;

-- ----------------------------
--  Table structure for `goods_type_tb`
-- ----------------------------
DROP TABLE IF EXISTS `goods_type_tb`;
CREATE TABLE `goods_type_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别Id',
  `type_id` VARCHAR(50)  DEFAULT NULL COMMENT '类目类型Id',
  `goods_id` varchar(200) DEFAULT NULL COMMENT '商品id',
  `menu_id` varchar(200) DEFAULT NULL COMMENT '商品套餐id',
  `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `status` int(10) DEFAULT '1' COMMENT '类别状态1-正常,2-已废弃',
  `seller_id` varchar(50) DEFAULT NULL COMMENT '卖家id',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='商品类目表' ENGINE=InnoDB AUTO_INCREMENT=10032 DEFAULT CHARSET=utf8;
COMMIT;
-- ----------------------------
--  Table structure for `goods_category_tb`
-- ----------------------------
DROP TABLE IF EXISTS `goods_category_tb`;
CREATE TABLE `goods_category_tb` (
  `id` VARCHAR(50)  not NULL COMMENT '类目Id',
  `parent_id` varchar(200) DEFAULT NULL COMMENT '父节点id',
  `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `status` int(10) DEFAULT '1' COMMENT '类别状态1-正常,2-已废弃',
  `seller_id` varchar(50) DEFAULT NULL COMMENT '卖家id',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='商品类目表' ENGINE=InnoDB  DEFAULT CHARSET=utf8;
COMMIT;

-- ----------------------------
--  Table structure for `goods_comment_info_tb`
-- ----------------------------
DROP TABLE IF EXISTS `goods_comment_info_tb`;
CREATE TABLE `goods_comment_info_tb` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品评论表Id',
  `commenter_id` varchar(50) DEFAULT NULL COMMENT '评论人id',
  `goods_id` varchar(200) DEFAULT NULL COMMENT '商品id',
  `menu_id` varchar(200) DEFAULT NULL COMMENT '商品套餐id',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='商品评论表' ENGINE=InnoDB AUTO_INCREMENT=3254 DEFAULT CHARSET=utf8;
COMMIT;

-- ----------------------------
--  Table structure for `goods_recomend_tb`
-- ----------------------------
DROP TABLE IF EXISTS `goods_recomend_tb`;
CREATE TABLE `goods_recomend_tb` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '推荐商品信息表Id',
  `seller_id` varchar(50) DEFAULT NULL COMMENT '卖家id',
  `goods_id` varchar(200) DEFAULT NULL COMMENT '商品id',
  `menu_id` varchar(200) DEFAULT NULL COMMENT '商品套餐id',
  `recomender` varchar(50) DEFAULT NULL COMMENT '推荐人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='推荐商品信息表' ENGINE=InnoDB AUTO_INCREMENT=736 DEFAULT CHARSET=utf8;
COMMIT;

-- ----------------------------
--  Table structure for `goods_like_info_tb`
-- ----------------------------
DROP TABLE IF EXISTS `goods_like_info_tb`;
CREATE TABLE `goods_like_info_tb` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '点赞信息表Id',
  `liker_id` varchar(50) DEFAULT NULL COMMENT '点赞人id',
  `goods_id` varchar(200) DEFAULT NULL COMMENT '商品id',
  `menu_id` varchar(200) DEFAULT NULL COMMENT '商品套餐id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='点赞信息表' ENGINE=InnoDB AUTO_INCREMENT=1589 DEFAULT CHARSET=utf8;
COMMIT;
-- ----------------------------
--  Table structure for `goods_examine_info_tb`
-- ----------------------------
DROP TABLE IF EXISTS `goods_examine_info_tb`;
CREATE TABLE `goods_examine_info_tb` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品审核信息表Id',
  `goods_id` varchar(200) DEFAULT NULL COMMENT '商品id',
  `menu_id` varchar(200) DEFAULT NULL COMMENT '商品套餐id',
  `examine_result` int(10) NULL COMMENT '审核信息',
  `examine_time` datetime DEFAULT NULL COMMENT '审核时间',   
  `examiner` varchar(50) DEFAULT NULL COMMENT '审核人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='点赞信息表' ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
COMMIT;

-- ----------------------------
--  Table structure for `media_info_tb`
-- ----------------------------
DROP TABLE IF EXISTS `media_info_tb`;
CREATE TABLE `media_info_tb` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '媒体信息表Id',
  `entity_id` varchar(50) DEFAULT NULL  COMMENT '卖家/门户Id',
  `goods_id` varchar(200) DEFAULT NULL COMMENT '商品id',
  `menu_id` varchar(200) DEFAULT NULL COMMENT '商品套餐id',
  `hot_sell_id` varchar(200) DEFAULT NULL COMMENT '商品热销id',
  `absolute_path` varchar(200) DEFAULT NULL COMMENT '文件绝对路径',
  `relative_path` varchar(200) DEFAULT NULL COMMENT '文件相对路径',
  `type` int(10) DEFAULT NULL COMMENT '文件类型,10-图片,20-视频',
  `name` varchar(100) DEFAULT NULL COMMENT '文件名',
  -- 不清楚
  NOCOUNT varchar(100) DEFAULT NULL COMMENT '编号（序列号）',
  -- 不清楚
  lllustrate varchar(100) DEFAULT NULL COMMENT '说明信息',
  -- 不清楚
  leading_officical varchar(100) DEFAULT NULL COMMENT '负责人',
  -- 不清楚
  location varchar(500) DEFAULT NULL COMMENT '位置',
  -- 不清楚
  remark varchar(50) DEFAULT NULL COMMENT '标记',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='媒体信息，包括用户形象（卖家（公司）形像、个人形象等）表' ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
COMMIT;

-- ----------------------------
--  Table structure for `goods_hot_sell_tb`
-- ----------------------------
DROP TABLE IF EXISTS `goods_hot_sell_tb`;
CREATE TABLE `goods_hot_sell_tb` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `hot_sell_id` varchar(200) DEFAULT NULL COMMENT '商品热销id',
  `goods_id` varchar(200) DEFAULT NULL COMMENT '商品id',
  `menu_id` varchar(200) DEFAULT NULL COMMENT '商品套餐id',
  `hot_set_desc` int(10) NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='商品热销表' ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
COMMIT;