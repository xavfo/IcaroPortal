# EMS MySQL Manager Pro 3.1.1.1
# ---------------------------------------
# Host     : 192.168.0.25
# Port     : 3306
# Database : icaro


SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `icaro`;

CREATE DATABASE `icaro`
    CHARACTER SET 'latin1'
    COLLATE 'latin1_swedish_ci';

USE `icaro`;

#
# Structure for the `tb_access_url` table : 
#

DROP TABLE IF EXISTS `tb_access_url`;

CREATE TABLE `tb_access_url` (
  `access_url_code` int(11) NOT NULL default '0',
  `access_url_name` varchar(200) NOT NULL default '',
  `access_url_path` varchar(255) default NULL,
  `access_url_class_name` varchar(255) default NULL,
  PRIMARY KEY  (`access_url_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tipo de acceso a donde apunta el acceso directo relacionado';

#
# Structure for the `tb_access` table : 
#

DROP TABLE IF EXISTS `tb_access`;

CREATE TABLE `tb_access` (
  `access_code` int(11) NOT NULL default '0',
  `access_url_code` int(11) NOT NULL default '0',
  `access_name` varchar(200) NOT NULL default '',
  `access_url` varchar(250) default NULL,
  `access_path` varchar(255) NOT NULL default '',
  `access_height` int(11) default NULL,
  `access_width` int(11) default NULL,
  `access_related_code` int(11) default NULL,
  `access_related_title` varchar(200) default NULL,
  `access_desc` text,
  PRIMARY KEY  (`access_code`),
  KEY `relationship_130_fk` (`access_url_code`),
  CONSTRAINT `fk_relationship_130` FOREIGN KEY (`access_url_code`) REFERENCES `tb_access_url` (`access_url_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tabla antigua de banners para accesos directos a contenidos,';

#
# Structure for the `tb_access_level` table : 
#

DROP TABLE IF EXISTS `tb_access_level`;

CREATE TABLE `tb_access_level` (
  `access_level_code` int(11) NOT NULL default '0',
  `access_level_name` varchar(200) NOT NULL default '',
  `access_level_key` varchar(150) NOT NULL default '',
  `access_level_enabled` decimal(1,0) NOT NULL default '0',
  PRIMARY KEY  (`access_level_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_language` table : 
#

DROP TABLE IF EXISTS `tb_language`;

CREATE TABLE `tb_language` (
  `language_code` int(11) NOT NULL default '0',
  `language_default` decimal(1,0) NOT NULL default '0',
  `language_enabled` decimal(1,0) NOT NULL default '0',
  `language_name` varchar(60) NOT NULL default '',
  `language_locale` char(2) NOT NULL default '',
  `language_integer_format` varchar(20) NOT NULL default '',
  `language_float_format` varchar(20) NOT NULL default '',
  `language_currency_format` varchar(20) NOT NULL default '',
  `language_date_format` varchar(20) NOT NULL default '',
  `language_time_format` varchar(20) NOT NULL default '',
  `language_date_time_format` varchar(20) NOT NULL default '',
  `language_short_date_format` varchar(20) default NULL,
  `language_long_date_format` varchar(20) default NULL,
  `language_charset` varchar(20) default NULL,
  PRIMARY KEY  (`language_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_menu` table : 
#

DROP TABLE IF EXISTS `tb_menu`;

CREATE TABLE `tb_menu` (
  `menu_code` int(11) NOT NULL default '0',
  `menu_enabled` decimal(1,0) NOT NULL default '0',
  `menu_level` int(11) default NULL,
  PRIMARY KEY  (`menu_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='alamacena las zonas de menú a las que se relaciona una item';

#
# Structure for the `tb_menu_language` table : 
#

DROP TABLE IF EXISTS `tb_menu_language`;

CREATE TABLE `tb_menu_language` (
  `menu_lang_code` int(11) NOT NULL default '0',
  `language_code` int(11) NOT NULL default '0',
  `menu_code` int(11) NOT NULL default '0',
  `menu_lang_name` varchar(200) NOT NULL default '',
  PRIMARY KEY  (`menu_lang_code`),
  KEY `rel_language_menu_type_fk` (`language_code`),
  KEY `rel_menu_type_language_fk` (`menu_code`),
  CONSTRAINT `fk_rel_language_menu_type` FOREIGN KEY (`language_code`) REFERENCES `tb_language` (`language_code`),
  CONSTRAINT `fk_rel_menu_type_language` FOREIGN KEY (`menu_code`) REFERENCES `tb_menu` (`menu_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_display_mode` table : 
#

DROP TABLE IF EXISTS `tb_display_mode`;

CREATE TABLE `tb_display_mode` (
  `display_mode_code` int(11) NOT NULL default '0',
  `display_mode_name` varchar(200) NOT NULL default '',
  `display_mode_key` varchar(150) NOT NULL default '',
  `display_mode_enabled` decimal(1,0) NOT NULL default '0',
  PRIMARY KEY  (`display_mode_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_access_menu` table : 
#

DROP TABLE IF EXISTS `tb_access_menu`;

CREATE TABLE `tb_access_menu` (
  `access_menu_code` int(11) NOT NULL default '0',
  `menu_lang_code` int(11) NOT NULL default '0',
  `access_code` int(11) NOT NULL default '0',
  `display_mode_code` int(11) default NULL,
  `access_menu_order` int(11) NOT NULL default '0',
  `access_menu_enabled` decimal(1,0) NOT NULL default '0',
  `access_menu_width` int(11) default NULL,
  `access_menu_positionx` int(11) default NULL,
  `access_menu_positiony` int(11) default NULL,
  PRIMARY KEY  (`access_menu_code`),
  KEY `relationship_126_fk` (`access_code`),
  KEY `relationship_127_fk` (`menu_lang_code`),
  KEY `relationship_128_fk` (`display_mode_code`),
  CONSTRAINT `fk_relationship_126` FOREIGN KEY (`access_code`) REFERENCES `tb_access` (`access_code`),
  CONSTRAINT `fk_relationship_127` FOREIGN KEY (`menu_lang_code`) REFERENCES `tb_menu_language` (`menu_lang_code`),
  CONSTRAINT `fk_relationship_128` FOREIGN KEY (`display_mode_code`) REFERENCES `tb_display_mode` (`display_mode_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='acceso directo que aparecen en un menu item';

#
# Structure for the `tb_country` table : 
#

DROP TABLE IF EXISTS `tb_country`;

CREATE TABLE `tb_country` (
  `country_code` int(11) NOT NULL default '0',
  `country_name` varchar(60) NOT NULL default '',
  `country_iso` char(3) default NULL,
  `country_key` varchar(150) default NULL,
  `country_enabled` decimal(1,0) default NULL,
  `country_home_delivery` decimal(1,0) default '0',
  PRIMARY KEY  (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='catálogo de países.';

#
# Structure for the `tb_state` table : 
#

DROP TABLE IF EXISTS `tb_state`;

CREATE TABLE `tb_state` (
  `state_code` int(11) NOT NULL default '0',
  `country_code` int(11) NOT NULL default '0',
  `state_name` varchar(60) NOT NULL default '',
  `state_key` varchar(150) default NULL,
  `state_enabled` decimal(1,0) default NULL,
  PRIMARY KEY  (`state_code`),
  KEY `relationship_7_fk` (`country_code`),
  CONSTRAINT `fk_relationship_7` FOREIGN KEY (`country_code`) REFERENCES `tb_country` (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='catalogo de esdados o provincias que se encuentran registrad';

#
# Structure for the `tb_city` table : 
#

DROP TABLE IF EXISTS `tb_city`;

CREATE TABLE `tb_city` (
  `city_code` int(11) NOT NULL default '0',
  `state_code` int(11) NOT NULL default '0',
  `city_name` varchar(80) NOT NULL default '',
  `city_enabled` decimal(1,0) NOT NULL default '0',
  `city_home_delivery` decimal(1,0) NOT NULL default '0',
  `city_key` varchar(150) default NULL,
  PRIMARY KEY  (`city_code`),
  KEY `relationship_9_fk` (`state_code`),
  CONSTRAINT `fk_relationship_8` FOREIGN KEY (`state_code`) REFERENCES `tb_state` (`state_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='catálogo de ciudades.';

#
# Structure for the `tb_airport` table : 
#

DROP TABLE IF EXISTS `tb_airport`;

CREATE TABLE `tb_airport` (
  `airport_code` int(11) NOT NULL default '0',
  `city_code` int(11) NOT NULL default '0',
  `airport_name` varchar(200) NOT NULL default '',
  `airport_iata_code` varchar(20) NOT NULL default '',
  `airport_enabled` decimal(1,0) NOT NULL default '0',
  PRIMARY KEY  (`airport_code`),
  KEY `relationship_139_fk` (`city_code`),
  CONSTRAINT `fk_relationship_139` FOREIGN KEY (`city_code`) REFERENCES `tb_city` (`city_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='listado de aeropuertos disponibles en el sistema.';

#
# Structure for the `tb_area` table : 
#

DROP TABLE IF EXISTS `tb_area`;

CREATE TABLE `tb_area` (
  `area_code` int(11) NOT NULL default '0',
  `area_name` varchar(200) default NULL,
  `area_email` varchar(200) default NULL,
  `area_enabled` decimal(1,0) default NULL,
  PRIMARY KEY  (`area_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_log` table : 
#

DROP TABLE IF EXISTS `tb_log`;

CREATE TABLE `tb_log` (
  `log_code` int(11) NOT NULL default '0',
  `log_user_code` int(11) NOT NULL default '0',
  `log_user_name` varchar(200) NOT NULL default '',
  `log_role_code` int(11) NOT NULL default '0',
  `log_role_name` varchar(200) NOT NULL default '',
  `log_in_date` date NOT NULL default '0000-00-00',
  `log_out_date` date default NULL,
  PRIMARY KEY  (`log_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_audit` table : 
#

DROP TABLE IF EXISTS `tb_audit`;

CREATE TABLE `tb_audit` (
  `audit_code` int(11) NOT NULL default '0',
  `log_code` int(11) NOT NULL default '0',
  `audit_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `audit_class` text NOT NULL,
  `audit_entity` int(11) NOT NULL default '0',
  `audit_action` varchar(15) NOT NULL default '',
  `audit_module` text,
  `audit_method` varchar(10) default NULL,
  `audit_uri` varchar(255) default NULL,
  `audit_url` varchar(255) default NULL,
  `audit_remote_address` varchar(20) default NULL,
  `audit_remote_host` varchar(150) default NULL,
  `audit_entity_name` text,
  PRIMARY KEY  (`audit_code`),
  KEY `rel_log_audit_fk` (`log_code`),
  CONSTRAINT `fk_rel_log_audit` FOREIGN KEY (`log_code`) REFERENCES `tb_log` (`log_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_brand` table : 
#

DROP TABLE IF EXISTS `tb_brand`;

CREATE TABLE `tb_brand` (
  `brand_code` int(11) NOT NULL default '0',
  `brand_name` varchar(200) NOT NULL default '',
  `brand_logo` varchar(255) default NULL,
  `brand_url` varchar(255) default NULL,
  `brand_image` varchar(255) default NULL,
  `brand_description` text,
  PRIMARY KEY  (`brand_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='fabricante o marca del producto ofertado.';

#
# Structure for the `tb_topic` table : 
#

DROP TABLE IF EXISTS `tb_topic`;

CREATE TABLE `tb_topic` (
  `topic_code` int(11) NOT NULL default '0',
  `topic_name` varchar(200) default NULL,
  `topic_description` varchar(255) default NULL,
  `topic_enabled` decimal(1,0) default NULL,
  PRIMARY KEY  (`topic_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_bulletin` table : 
#

DROP TABLE IF EXISTS `tb_bulletin`;

CREATE TABLE `tb_bulletin` (
  `bulletin_code` int(11) NOT NULL default '0',
  `topic_code` int(11) NOT NULL default '0',
  `bulletin_subject` varchar(200) NOT NULL default '',
  `bulletin_title` varchar(200) NOT NULL default '',
  `bulletin_image` varchar(255) default NULL,
  `bulletin_content` text NOT NULL,
  `bulletin_last_update_date` datetime default NULL,
  `bulletin_last_update_user` varchar(200) default NULL,
  PRIMARY KEY  (`bulletin_code`),
  KEY `rel_topic_bulletin_fk` (`topic_code`),
  CONSTRAINT `fk_rel_topic_bulletin` FOREIGN KEY (`topic_code`) REFERENCES `tb_topic` (`topic_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_catalogue_type` table : 
#

DROP TABLE IF EXISTS `tb_catalogue_type`;

CREATE TABLE `tb_catalogue_type` (
  `catalogue_type_code` int(11) NOT NULL default '0',
  `catalogue_type_name` varchar(200) default NULL,
  PRIMARY KEY  (`catalogue_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='catálogo de catálogos: tipos de cuentas bancarias, priorid';

#
# Structure for the `tb_catalogue` table : 
#

DROP TABLE IF EXISTS `tb_catalogue`;

CREATE TABLE `tb_catalogue` (
  `catalogue_code` int(11) NOT NULL default '0',
  `catalogue_type_code` int(11) NOT NULL default '0',
  `catalogue_enabled` decimal(1,0) NOT NULL default '0',
  `catalogue_name` varchar(200) NOT NULL default '',
  `catalogue_icon` varchar(255) default NULL,
  `catalogue_description` text,
  `catalogue_email` varchar(200) default NULL,
  `catalogue_administrator` varchar(200) default NULL,
  PRIMARY KEY  (`catalogue_code`),
  KEY `relationship_74_fk` (`catalogue_type_code`),
  CONSTRAINT `fk_relationship_74` FOREIGN KEY (`catalogue_type_code`) REFERENCES `tb_catalogue_type` (`catalogue_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='catálogo de catálogos: tipos de cuentas bancarias, priorid';

#
# Structure for the `tb_category` table : 
#

DROP TABLE IF EXISTS `tb_category`;

CREATE TABLE `tb_category` (
  `category_code` int(11) NOT NULL default '0',
  `category_parent` int(11) default NULL,
  `category_name` varchar(200) NOT NULL default '',
  `category_level` decimal(4,0) NOT NULL default '0',
  `category_group` decimal(1,0) NOT NULL default '0',
  `category_enabled` decimal(1,0) NOT NULL default '0',
  `category_order_index` decimal(4,0) NOT NULL default '0',
  `category_featured` decimal(1,0) NOT NULL default '0',
  `category_image` varchar(255) default NULL,
  `category_description` text,
  PRIMARY KEY  (`category_code`),
  KEY `relationship_26_fk` (`category_parent`),
  CONSTRAINT `fk_relationship_26` FOREIGN KEY (`category_parent`) REFERENCES `tb_category` (`category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='categorías de de clasificación fybeca para productos, art?';

#
# Structure for the `tb_checkout_step` table : 
#

DROP TABLE IF EXISTS `tb_checkout_step`;

CREATE TABLE `tb_checkout_step` (
  `checkout_step_code` int(11) NOT NULL default '0',
  `checkout_step_name` varchar(200) NOT NULL default '',
  `checkout_step_order` int(11) NOT NULL default '0',
  `checkout_step_icon` varchar(255) NOT NULL default '',
  `checkout_step_thumbnail` varchar(255) NOT NULL default '',
  `checkout_step_thumbnail_rollover` varchar(255) default NULL,
  `checkout_step_next` int(11) default NULL,
  `checkout_step_description` text,
  PRIMARY KEY  (`checkout_step_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='conjunto de pasos para la realización de una compra en lín';

#
# Structure for the `tb_contact_form` table : 
#

DROP TABLE IF EXISTS `tb_contact_form`;

CREATE TABLE `tb_contact_form` (
  `contact_code` int(11) NOT NULL default '0',
  `catalogue_code` int(11) default NULL,
  `country_code` int(11) default NULL,
  `contact_name` varchar(200) NOT NULL default '',
  `contact_last_name` varchar(200) NOT NULL default '',
  `contact_email` varchar(200) NOT NULL default '',
  `contact_company` varchar(200) default NULL,
  `contact_position` varchar(200) default NULL,
  `contact_comment` text NOT NULL,
  `contact_address` varchar(250) default NULL,
  `contact_phone` varchar(20) default NULL,
  `contact_city` varchar(200) default NULL,
  `contact_state` varchar(200) default NULL,
  PRIMARY KEY  (`contact_code`),
  KEY `relationship_contact_catalog_fk` (`catalogue_code`),
  KEY `rel_country_contact_form_fk` (`country_code`),
  CONSTRAINT `fk_relationship_contact_catalog` FOREIGN KEY (`catalogue_code`) REFERENCES `tb_catalogue` (`catalogue_code`),
  CONSTRAINT `fk_rel_country_contact_form` FOREIGN KEY (`country_code`) REFERENCES `tb_country` (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_image_category` table : 
#

DROP TABLE IF EXISTS `tb_image_category`;

CREATE TABLE `tb_image_category` (
  `image_category_code` int(11) NOT NULL default '0',
  `image_category_parent` int(11) default NULL,
  `image_category_name` varchar(200) NOT NULL default '',
  PRIMARY KEY  (`image_category_code`),
  KEY `rel_parent_image_category_fk` (`image_category_parent`),
  CONSTRAINT `fk_rel_parent_image_category` FOREIGN KEY (`image_category_parent`) REFERENCES `tb_image_category` (`image_category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_image` table : 
#

DROP TABLE IF EXISTS `tb_image`;

CREATE TABLE `tb_image` (
  `image_code` int(11) NOT NULL default '0',
  `image_category_code` int(11) NOT NULL default '0',
  `image_name` varchar(200) NOT NULL default '',
  `image_path` varchar(255) NOT NULL default '',
  `image_height` int(11) NOT NULL default '0',
  `image_width` int(11) NOT NULL default '0',
  PRIMARY KEY  (`image_code`),
  KEY `rel_image_category_image_fk` (`image_category_code`),
  CONSTRAINT `fk_rel_image_category_image` FOREIGN KEY (`image_category_code`) REFERENCES `tb_image_category` (`image_category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_portal_module` table : 
#

DROP TABLE IF EXISTS `tb_portal_module`;

CREATE TABLE `tb_portal_module` (
  `portal_module_code` int(11) NOT NULL default '0',
  `portal_module_name` varchar(200) NOT NULL default '',
  `portal_module_key` varchar(150) NOT NULL default '',
  `portal_module_forward` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`portal_module_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_layout` table : 
#

DROP TABLE IF EXISTS `tb_layout`;

CREATE TABLE `tb_layout` (
  `layout_code` int(11) NOT NULL default '0',
  `portal_module_code` int(11) NOT NULL default '0',
  `layout_forward` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`layout_code`),
  KEY `rel_module_layout_fk` (`portal_module_code`),
  CONSTRAINT `fk_rel_module_layout` FOREIGN KEY (`portal_module_code`) REFERENCES `tb_portal_module` (`portal_module_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_content` table : 
#

DROP TABLE IF EXISTS `tb_content`;

CREATE TABLE `tb_content` (
  `content_code` int(11) NOT NULL default '0',
  `access_level_code` int(11) NOT NULL default '0',
  `layout_code` int(11) default NULL,
  `image_code` int(11) default NULL,
  `content_parent` int(11) default NULL,
  `image_access_code` int(11) default NULL,
  `language_code` int(11) NOT NULL default '0',
  `content_title` varchar(200) NOT NULL default '',
  `content_intro` text,
  `content_text` text,
  `content_level` int(11) NOT NULL default '0',
  `content_group` decimal(1,0) NOT NULL default '0',
  `content_order` int(11) NOT NULL default '0',
  `content_background` varchar(255) default NULL,
  `content_enabled` decimal(1,0) default NULL,
  `content_keywords` varchar(150) NOT NULL default '',
  `content_description` varchar(255) default NULL,
  `content_show_open` decimal(1,0) NOT NULL default '0',
  `content_type` decimal(1,0) NOT NULL default '0',
  `content_link` varchar(250) default NULL,
  `content_main` decimal(1,0) default NULL,
  `content_menu_alias` varchar(200) default NULL,
  `content_docs_count` int(11) default NULL,
  `content_support_docs_count` int(11) default NULL,
  `content_faqs_count` int(11) default NULL,
  `content_links_count` int(11) default NULL,
  PRIMARY KEY  (`content_code`),
  KEY `relationship_access_pic_fk` (`image_code`),
  KEY `relationship_picture_fk` (`image_access_code`),
  KEY `relationship_parent_fk` (`content_parent`),
  KEY `relationship_lang_content_fk` (`language_code`),
  KEY `rel_access_level_content_fk` (`access_level_code`),
  KEY `rel_layout_content_fk` (`layout_code`),
  CONSTRAINT `fk_relationship_access_pic` FOREIGN KEY (`image_code`) REFERENCES `tb_image` (`image_code`),
  CONSTRAINT `fk_relationship_lang_content` FOREIGN KEY (`language_code`) REFERENCES `tb_language` (`language_code`),
  CONSTRAINT `fk_relationship_parent` FOREIGN KEY (`content_parent`) REFERENCES `tb_content` (`content_code`),
  CONSTRAINT `fk_relationship_picture` FOREIGN KEY (`image_access_code`) REFERENCES `tb_image` (`image_code`),
  CONSTRAINT `fk_rel_access_level_content` FOREIGN KEY (`access_level_code`) REFERENCES `tb_access_level` (`access_level_code`),
  CONSTRAINT `fk_rel_layout_content` FOREIGN KEY (`layout_code`) REFERENCES `tb_layout` (`layout_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_document_type` table : 
#

DROP TABLE IF EXISTS `tb_document_type`;

CREATE TABLE `tb_document_type` (
  `document_type_code` int(11) NOT NULL default '0',
  `document_type_name` varchar(80) default NULL,
  PRIMARY KEY  (`document_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='almacena los tipos de archivos xls, doc, etc';

#
# Structure for the `tb_document_display_mode` table : 
#

DROP TABLE IF EXISTS `tb_document_display_mode`;

CREATE TABLE `tb_document_display_mode` (
  `doc_display_mode_code` int(11) NOT NULL default '0',
  `doc_display_mode_name` varchar(200) NOT NULL default '',
  PRIMARY KEY  (`doc_display_mode_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tabla tipo, almacena los tipos de documentos 1:documentos y ';

#
# Structure for the `tb_doc_container` table : 
#

DROP TABLE IF EXISTS `tb_doc_container`;

CREATE TABLE `tb_doc_container` (
  `doc_container_code` int(11) NOT NULL default '0',
  `doc_display_mode_code` int(11) NOT NULL default '0',
  `catalogue_code` int(11) NOT NULL default '0',
  `document_type_code` int(11) NOT NULL default '0',
  `doc_container_title` varchar(200) NOT NULL default '',
  `doc_container_description` text NOT NULL,
  `doc_container_keywords` text,
  `doc_container_from` date default NULL,
  `doc_container_to` date default NULL,
  `doc_container_path` varchar(255) NOT NULL default '',
  `doc_container_enabled` decimal(1,0) NOT NULL default '0',
  `doc_container_size` decimal(10,0) NOT NULL default '0',
  `doc_container_extension` varchar(30) default NULL,
  PRIMARY KEY  (`doc_container_code`),
  KEY `relationship_doc_catalog_fk` (`catalogue_code`),
  KEY `relationship_76_fk` (`document_type_code`),
  KEY `rel_doc_display_mode_doc_fk` (`doc_display_mode_code`),
  CONSTRAINT `fk_relationship_76` FOREIGN KEY (`document_type_code`) REFERENCES `tb_document_type` (`document_type_code`),
  CONSTRAINT `fk_relationship_doc_catalog` FOREIGN KEY (`catalogue_code`) REFERENCES `tb_catalogue` (`catalogue_code`),
  CONSTRAINT `fk_rel_doc_display_mode_doc` FOREIGN KEY (`doc_display_mode_code`) REFERENCES `tb_document_display_mode` (`doc_display_mode_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_content_document` table : 
#

DROP TABLE IF EXISTS `tb_content_document`;

CREATE TABLE `tb_content_document` (
  `content_code` int(11) NOT NULL default '0',
  `doc_container_code` int(11) NOT NULL default '0',
  PRIMARY KEY  (`content_code`,`doc_container_code`),
  KEY `content_document_fk` (`content_code`),
  KEY `content_document2_fk` (`doc_container_code`),
  CONSTRAINT `fk_content_document` FOREIGN KEY (`content_code`) REFERENCES `tb_content` (`content_code`),
  CONSTRAINT `fk_content_document2` FOREIGN KEY (`doc_container_code`) REFERENCES `tb_doc_container` (`doc_container_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_faq_container` table : 
#

DROP TABLE IF EXISTS `tb_faq_container`;

CREATE TABLE `tb_faq_container` (
  `faq_container_code` int(11) NOT NULL default '0',
  `catalogue_code` int(11) NOT NULL default '0',
  `faq_container_question` text NOT NULL,
  `faq_container_answer` text NOT NULL,
  `faq_container_from` date default NULL,
  `faq_container_to` date default NULL,
  `faq_container_enabled` decimal(1,0) NOT NULL default '0',
  `faq_container_index` int(11) default NULL,
  PRIMARY KEY  (`faq_container_code`),
  KEY `relationship_78_fk` (`catalogue_code`),
  CONSTRAINT `fk_relationship_78` FOREIGN KEY (`catalogue_code`) REFERENCES `tb_catalogue` (`catalogue_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_content_faq` table : 
#

DROP TABLE IF EXISTS `tb_content_faq`;

CREATE TABLE `tb_content_faq` (
  `content_code` int(11) NOT NULL default '0',
  `faq_container_code` int(11) NOT NULL default '0',
  PRIMARY KEY  (`content_code`,`faq_container_code`),
  KEY `content_faq_fk` (`content_code`),
  KEY `content_faq2_fk` (`faq_container_code`),
  CONSTRAINT `fk_content_faq` FOREIGN KEY (`content_code`) REFERENCES `tb_content` (`content_code`),
  CONSTRAINT `fk_content_faq2` FOREIGN KEY (`faq_container_code`) REFERENCES `tb_faq_container` (`faq_container_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_image_galery` table : 
#

DROP TABLE IF EXISTS `tb_image_galery`;

CREATE TABLE `tb_image_galery` (
  `image_galery_code` int(11) NOT NULL default '0',
  `image_thumb_code` int(11) default NULL,
  `image_medium_code` int(11) default NULL,
  `image_large_code` int(11) NOT NULL default '0',
  `image_galery_title` varchar(200) NOT NULL default '',
  `image_galery_description` text,
  `image_galery_url` varchar(255) default NULL,
  `image_galery_from` date default NULL,
  `image_galery_to` date default NULL,
  `image_galery_enabled` decimal(1,0) NOT NULL default '0',
  PRIMARY KEY  (`image_galery_code`),
  KEY `rel_image_galery0_fk` (`image_large_code`),
  KEY `rel_image_galery1_fk` (`image_medium_code`),
  KEY `rel_image_galery2_fk` (`image_thumb_code`),
  CONSTRAINT `fk_rel_image_galery0` FOREIGN KEY (`image_large_code`) REFERENCES `tb_image` (`image_code`),
  CONSTRAINT `fk_rel_image_galery1` FOREIGN KEY (`image_medium_code`) REFERENCES `tb_image` (`image_code`),
  CONSTRAINT `fk_rel_image_galery2` FOREIGN KEY (`image_thumb_code`) REFERENCES `tb_image` (`image_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_content_image_galery` table : 
#

DROP TABLE IF EXISTS `tb_content_image_galery`;

CREATE TABLE `tb_content_image_galery` (
  `image_galery_code` int(11) NOT NULL default '0',
  `content_code` int(11) NOT NULL default '0',
  PRIMARY KEY  (`image_galery_code`,`content_code`),
  KEY `content_image_galery_fk` (`image_galery_code`),
  KEY `content_image_galery2_fk` (`content_code`),
  CONSTRAINT `fk_content_image_galery` FOREIGN KEY (`image_galery_code`) REFERENCES `tb_image_galery` (`image_galery_code`),
  CONSTRAINT `fk_content_image_galery2` FOREIGN KEY (`content_code`) REFERENCES `tb_content` (`content_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_link_type` table : 
#

DROP TABLE IF EXISTS `tb_link_type`;

CREATE TABLE `tb_link_type` (
  `link_type_code` int(11) NOT NULL default '0',
  `link_type_name` varchar(200) NOT NULL default '',
  PRIMARY KEY  (`link_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='typos de links 1:general 2:portlet 3:aplication';

#
# Structure for the `tb_link_container` table : 
#

DROP TABLE IF EXISTS `tb_link_container`;

CREATE TABLE `tb_link_container` (
  `link_container_code` int(11) NOT NULL default '0',
  `catalogue_code` int(11) NOT NULL default '0',
  `link_type_code` int(11) NOT NULL default '0',
  `link_container_title` varchar(200) NOT NULL default '',
  `link_container_url` varchar(255) NOT NULL default '',
  `link_container_description` text,
  `link_container_from` date default NULL,
  `link_container_to` date default NULL,
  `link_container_enabled` decimal(1,0) NOT NULL default '0',
  `link_container_index` decimal(10,0) default NULL,
  PRIMARY KEY  (`link_container_code`),
  KEY `category_fk` (`catalogue_code`),
  KEY `rel_link_type_link_cont_fk` (`link_type_code`),
  CONSTRAINT `fk_category` FOREIGN KEY (`catalogue_code`) REFERENCES `tb_catalogue` (`catalogue_code`),
  CONSTRAINT `fk_rel_link_type_link_cont` FOREIGN KEY (`link_type_code`) REFERENCES `tb_link_type` (`link_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_content_link` table : 
#

DROP TABLE IF EXISTS `tb_content_link`;

CREATE TABLE `tb_content_link` (
  `content_code` int(11) NOT NULL default '0',
  `link_container_code` int(11) NOT NULL default '0',
  PRIMARY KEY  (`content_code`,`link_container_code`),
  KEY `content_link_fk` (`content_code`),
  KEY `content_link2_fk` (`link_container_code`),
  CONSTRAINT `fk_content_link` FOREIGN KEY (`content_code`) REFERENCES `tb_content` (`content_code`),
  CONSTRAINT `fk_content_link2` FOREIGN KEY (`link_container_code`) REFERENCES `tb_link_container` (`link_container_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_id_type` table : 
#

DROP TABLE IF EXISTS `tb_id_type`;

CREATE TABLE `tb_id_type` (
  `id_type_code` int(11) NOT NULL default '0',
  `id_type_name` varchar(200) NOT NULL default '',
  PRIMARY KEY  (`id_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tipo de identificacion para registro de usuarios';

#
# Structure for the `tb_customer_type` table : 
#

DROP TABLE IF EXISTS `tb_customer_type`;

CREATE TABLE `tb_customer_type` (
  `customer_type_code` int(11) NOT NULL default '0',
  `customer_type_name` varchar(200) NOT NULL default '',
  `customer_type_enabled` decimal(1,0) NOT NULL default '0',
  PRIMARY KEY  (`customer_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='roles de clientes del portal. no se desea mesclar con usuari';

#
# Structure for the `tb_customer` table : 
#

DROP TABLE IF EXISTS `tb_customer`;

CREATE TABLE `tb_customer` (
  `customer_code` int(11) NOT NULL default '0',
  `country_code` int(11) NOT NULL default '0',
  `customer_type_code` int(11) NOT NULL default '0',
  `id_type_code` int(11) NOT NULL default '0',
  `customer_creation` datetime NOT NULL default '0000-00-00 00:00:00',
  `customer_email` varchar(200) NOT NULL default '',
  `customer_enabled` decimal(1,0) NOT NULL default '0',
  `customer_firstname` varchar(50) NOT NULL default '',
  `customer_lastname` varchar(50) NOT NULL default '',
  `customer_password` varchar(200) NOT NULL default '',
  `customer_gender` decimal(1,0) NOT NULL default '0',
  `customer_password_reset` decimal(1,0) NOT NULL default '0',
  `customer_bulletin` decimal(1,0) NOT NULL default '0',
  `customer_modified` datetime default NULL,
  `customer_last_login` datetime default NULL,
  `customer_birthdate` datetime default NULL,
  `customer_identity` varchar(20) default NULL,
  `customer_bill_to` varchar(200) default NULL,
  `customer_bill_id` varchar(20) default NULL,
  `customer_bill_address` text,
  `customer_bill_phone` varchar(20) default NULL,
  `customer_mobile` varchar(20) default NULL,
  `customer_message` text,
  `customer_home_street1` varchar(250) default NULL,
  `customer_home_street2` varchar(250) default NULL,
  `customer_home_phone` varchar(20) default NULL,
  `customer_home_reference` text,
  `customer_home_city` varchar(200) default NULL,
  `customer_home_state` varchar(200) default NULL,
  PRIMARY KEY  (`customer_code`),
  UNIQUE KEY `ak_ak_email` (`customer_email`),
  KEY `relationship_115_fk` (`country_code`),
  KEY `relationship_121_fk` (`id_type_code`),
  KEY `relationship_140_fk` (`customer_type_code`),
  CONSTRAINT `fk_relationship_115` FOREIGN KEY (`country_code`) REFERENCES `tb_country` (`country_code`),
  CONSTRAINT `fk_relationship_118` FOREIGN KEY (`id_type_code`) REFERENCES `tb_id_type` (`id_type_code`),
  CONSTRAINT `fk_relationship_140` FOREIGN KEY (`customer_type_code`) REFERENCES `tb_customer_type` (`customer_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='datos del cliente  que desea efectuar pedidos en línea usan';

#
# Structure for the `tb_route` table : 
#

DROP TABLE IF EXISTS `tb_route`;

CREATE TABLE `tb_route` (
  `route_code` int(11) NOT NULL default '0',
  `route_name` varchar(200) NOT NULL default '',
  `route_enabled` decimal(1,0) NOT NULL default '0',
  `route_notes` text,
  PRIMARY KEY  (`route_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='trayectos para destinos o tramos para vuelos disponibles.';

#
# Structure for the `tb_delivery_agency` table : 
#

DROP TABLE IF EXISTS `tb_delivery_agency`;

CREATE TABLE `tb_delivery_agency` (
  `delivery_agency_code` int(11) NOT NULL default '0',
  `state_code` int(11) NOT NULL default '0',
  `route_code` int(11) NOT NULL default '0',
  `city_code` int(11) default NULL,
  `delivery_agency_name` varchar(200) NOT NULL default '',
  `delivery_agency_enabled` decimal(1,0) NOT NULL default '0',
  `delivery_agency_responsable` varchar(200) default NULL,
  `delivery_agency_street1` varchar(250) NOT NULL default '',
  `delivery_agency_street2` varchar(250) default NULL,
  `delivery_agency_phone` varchar(20) default NULL,
  `delivery_agency_external_code` varchar(20) default NULL,
  `delivery_agency_reference` text,
  PRIMARY KEY  (`delivery_agency_code`),
  KEY `relationship_109_fk` (`state_code`),
  KEY `relationship_114_fk` (`city_code`),
  KEY `relationship_125_fk` (`route_code`),
  CONSTRAINT `fk_relationship_109` FOREIGN KEY (`state_code`) REFERENCES `tb_state` (`state_code`),
  CONSTRAINT `fk_relationship_114` FOREIGN KEY (`city_code`) REFERENCES `tb_city` (`city_code`),
  CONSTRAINT `fk_relationship_125` FOREIGN KEY (`route_code`) REFERENCES `tb_route` (`route_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='registro de agencias de courier disponibles para convertirse';

#
# Structure for the `tb_customer_address` table : 
#

DROP TABLE IF EXISTS `tb_customer_address`;

CREATE TABLE `tb_customer_address` (
  `customer_address_code` int(11) NOT NULL default '0',
  `customer_code` int(11) NOT NULL default '0',
  `city_code` int(11) default NULL,
  `delivery_agency_code` int(11) default NULL,
  `state_code` int(11) NOT NULL default '0',
  `customer_address_name` varchar(200) NOT NULL default '',
  `customer_address_street1` varchar(250) NOT NULL default '',
  `customer_address_street2` varchar(250) default NULL,
  `customer_address_phone` varchar(20) default NULL,
  `customer_address_reference` text,
  PRIMARY KEY  (`customer_address_code`),
  KEY `address_customer_fk` (`customer_code`),
  KEY `relationship_107_fk` (`city_code`),
  KEY `relationship_110_fk` (`delivery_agency_code`),
  KEY `relationship_113_fk` (`state_code`),
  CONSTRAINT `fk_address_customer` FOREIGN KEY (`customer_code`) REFERENCES `tb_customer` (`customer_code`),
  CONSTRAINT `fk_relationship_107` FOREIGN KEY (`city_code`) REFERENCES `tb_city` (`city_code`),
  CONSTRAINT `fk_relationship_110` FOREIGN KEY (`delivery_agency_code`) REFERENCES `tb_delivery_agency` (`delivery_agency_code`),
  CONSTRAINT `fk_relationship_113` FOREIGN KEY (`state_code`) REFERENCES `tb_state` (`state_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='direcciones de cliente';

#
# Structure for the `tb_establishment` table : 
#

DROP TABLE IF EXISTS `tb_establishment`;

CREATE TABLE `tb_establishment` (
  `establishment_code` int(11) NOT NULL default '0',
  `catalogue_code` int(11) NOT NULL default '0',
  `city_code` int(11) NOT NULL default '0',
  `establishment_name` varchar(200) NOT NULL default '',
  `establishment_enabled` decimal(1,0) NOT NULL default '0',
  `establishment_address` varchar(250) NOT NULL default '',
  `establishment_phone1` varchar(20) NOT NULL default '',
  `establishment_image` varchar(255) default NULL,
  `establishment_phone2` varchar(20) default NULL,
  `establishment_fax` varchar(20) default NULL,
  `establishment_email` varchar(200) default NULL,
  `establishment_schedule` text,
  `establishment_description` text,
  `establishment_contact_name` varchar(200) default NULL,
  `establishment_contact_position` varchar(200) default NULL,
  `establishment_last_contact` date default NULL,
  PRIMARY KEY  (`establishment_code`),
  KEY `relationship_133_fk` (`catalogue_code`),
  KEY `relationship_134_fk` (`city_code`),
  CONSTRAINT `fk_relationship_133` FOREIGN KEY (`catalogue_code`) REFERENCES `tb_catalogue` (`catalogue_code`),
  CONSTRAINT `fk_relationship_134` FOREIGN KEY (`city_code`) REFERENCES `tb_city` (`city_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='establecimientos afiliados al cliente como parte de red de s';

#
# Structure for the `tb_event` table : 
#

DROP TABLE IF EXISTS `tb_event`;

CREATE TABLE `tb_event` (
  `event_code` int(11) NOT NULL default '0',
  `image_code` int(11) default NULL,
  `image_parent` int(11) default NULL,
  `catalogue_code` int(11) NOT NULL default '0',
  `language_code` int(11) NOT NULL default '0',
  `event_enabled` decimal(1,0) NOT NULL default '0',
  `event_title` varchar(200) default NULL,
  `event_summary` text,
  `event_description` text NOT NULL,
  `event_subject` varchar(200) default NULL,
  `event_host` varchar(200) default NULL,
  `event_main` decimal(1,0) NOT NULL default '0',
  `event_audience` text,
  `event_publish_from` datetime default NULL,
  `event_publish_to` datetime default NULL,
  `event_keywords` text NOT NULL,
  `event_seminary` decimal(1,0) NOT NULL default '0',
  `event_creation` datetime NOT NULL default '0000-00-00 00:00:00',
  `event_index` decimal(10,0) default NULL,
  PRIMARY KEY  (`event_code`),
  KEY `relationship_img_event_leading_fk` (`image_parent`),
  KEY `relationship_img_event_fk` (`image_code`),
  KEY `relationship_72_fk` (`language_code`),
  KEY `rel_catalog_event_fk` (`catalogue_code`),
  CONSTRAINT `fk_relationship_72` FOREIGN KEY (`language_code`) REFERENCES `tb_language` (`language_code`),
  CONSTRAINT `fk_relationship_img_event` FOREIGN KEY (`image_code`) REFERENCES `tb_image` (`image_code`),
  CONSTRAINT `fk_relationship_img_event_leading` FOREIGN KEY (`image_parent`) REFERENCES `tb_image` (`image_code`),
  CONSTRAINT `fk_rel_catalog_event` FOREIGN KEY (`catalogue_code`) REFERENCES `tb_catalogue` (`catalogue_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_event_date` table : 
#

DROP TABLE IF EXISTS `tb_event_date`;

CREATE TABLE `tb_event_date` (
  `event_date_code` int(11) NOT NULL default '0',
  `event_code` int(11) NOT NULL default '0',
  `city_code` int(11) NOT NULL default '0',
  `event_date_from` date NOT NULL default '0000-00-00',
  `event_date_location` varchar(250) NOT NULL default '',
  `event_date_notes` text,
  `event_date_schedule` text NOT NULL,
  `event_date_to` datetime NOT NULL default '0000-00-00 00:00:00',
  `event_date_responsible_email` varchar(200) NOT NULL default '',
  `event_date_optional_email` varchar(200) default NULL,
  PRIMARY KEY  (`event_date_code`),
  KEY `relationship_45_fk` (`event_code`),
  KEY `relationship_46_fk` (`city_code`),
  CONSTRAINT `fk_relationship_45` FOREIGN KEY (`event_code`) REFERENCES `tb_event` (`event_code`),
  CONSTRAINT `fk_relationship_46` FOREIGN KEY (`city_code`) REFERENCES `tb_city` (`city_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_event_document` table : 
#

DROP TABLE IF EXISTS `tb_event_document`;

CREATE TABLE `tb_event_document` (
  `event_code` int(11) NOT NULL default '0',
  `doc_container_code` int(11) NOT NULL default '0',
  PRIMARY KEY  (`event_code`,`doc_container_code`),
  KEY `event_document_fk` (`event_code`),
  KEY `event_document2_fk` (`doc_container_code`),
  CONSTRAINT `fk_event_document` FOREIGN KEY (`event_code`) REFERENCES `tb_event` (`event_code`),
  CONSTRAINT `fk_event_document2` FOREIGN KEY (`doc_container_code`) REFERENCES `tb_doc_container` (`doc_container_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_event_galery` table : 
#

DROP TABLE IF EXISTS `tb_event_galery`;

CREATE TABLE `tb_event_galery` (
  `event_code` int(11) NOT NULL default '0',
  `image_galery_code` int(11) NOT NULL default '0',
  PRIMARY KEY  (`event_code`,`image_galery_code`),
  KEY `event_galery_fk` (`event_code`),
  KEY `event_galery2_fk` (`image_galery_code`),
  CONSTRAINT `fk_event_galery` FOREIGN KEY (`event_code`) REFERENCES `tb_event` (`event_code`),
  CONSTRAINT `fk_event_galery2` FOREIGN KEY (`image_galery_code`) REFERENCES `tb_image_galery` (`image_galery_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tabla de ruptura entre eventos e imagenes';

#
# Structure for the `tb_event_subscriber` table : 
#

DROP TABLE IF EXISTS `tb_event_subscriber`;

CREATE TABLE `tb_event_subscriber` (
  `event_subscriber_code` int(11) NOT NULL default '0',
  `event_code` int(11) NOT NULL default '0',
  `event_date_code` int(11) NOT NULL default '0',
  `event_subscriber_name` varchar(200) NOT NULL default '',
  `event_subscriber_lastname` varchar(200) NOT NULL default '',
  `event_subscriber_address` varchar(250) default NULL,
  `event_subscriber_phone` varchar(20) NOT NULL default '',
  `event_subscriber_city` varchar(200) default NULL,
  `event_subscriber_email` varchar(200) NOT NULL default '',
  `event_subscriber_enterprise` varchar(200) default NULL,
  `event_subscriber_occupation` varchar(200) NOT NULL default '',
  `event_subscriber_system_date` date default NULL,
  PRIMARY KEY  (`event_subscriber_code`),
  KEY `rel_event_subscriber_fk` (`event_code`),
  KEY `rel_even_date_event_subscriber_fk` (`event_date_code`),
  CONSTRAINT `fk_rel_event_subscriber` FOREIGN KEY (`event_code`) REFERENCES `tb_event` (`event_code`),
  CONSTRAINT `fk_rel_even_date_event_subscriber` FOREIGN KEY (`event_date_code`) REFERENCES `tb_event_date` (`event_date_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_faq_document` table : 
#

DROP TABLE IF EXISTS `tb_faq_document`;

CREATE TABLE `tb_faq_document` (
  `faq_container_code` int(11) NOT NULL default '0',
  `doc_container_code` int(11) NOT NULL default '0',
  PRIMARY KEY  (`faq_container_code`,`doc_container_code`),
  KEY `faq_document_fk` (`faq_container_code`),
  KEY `faq_document2_fk` (`doc_container_code`),
  CONSTRAINT `fk_faq_document` FOREIGN KEY (`faq_container_code`) REFERENCES `tb_faq_container` (`faq_container_code`),
  CONSTRAINT `fk_faq_document2` FOREIGN KEY (`doc_container_code`) REFERENCES `tb_doc_container` (`doc_container_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_frequency` table : 
#

DROP TABLE IF EXISTS `tb_frequency`;

CREATE TABLE `tb_frequency` (
  `frequency_code` int(11) NOT NULL default '0',
  `route_code` int(11) NOT NULL default '0',
  `frequency_name` varchar(200) NOT NULL default '',
  `frequency_enabled` decimal(1,0) NOT NULL default '0',
  `frequency_notes` text,
  PRIMARY KEY  (`frequency_code`),
  KEY `relationship_135_fk` (`route_code`),
  CONSTRAINT `fk_relationship_135` FOREIGN KEY (`route_code`) REFERENCES `tb_route` (`route_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='frecuencias de vuelos para la ruta especificada, ejemplo lun';

#
# Structure for the `tb_flight` table : 
#

DROP TABLE IF EXISTS `tb_flight`;

CREATE TABLE `tb_flight` (
  `flight_code` int(11) NOT NULL default '0',
  `frequency_code` int(11) NOT NULL default '0',
  `flight_number` varchar(20) NOT NULL default '',
  `flight_enabled` decimal(1,0) NOT NULL default '0',
  `flight_departure` datetime NOT NULL default '0000-00-00 00:00:00',
  `flight_arrival` datetime NOT NULL default '0000-00-00 00:00:00',
  `flight_notes` text,
  PRIMARY KEY  (`flight_code`),
  KEY `relationship_136_fk` (`frequency_code`),
  CONSTRAINT `fk_relationship_136` FOREIGN KEY (`frequency_code`) REFERENCES `tb_frequency` (`frequency_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='datos de vuelos';

#
# Structure for the `tb_historic` table : 
#

DROP TABLE IF EXISTS `tb_historic`;

CREATE TABLE `tb_historic` (
  `historic_code` int(11) NOT NULL default '0',
  `bulletin_code` int(11) NOT NULL default '0',
  `historic_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `historic_user_code` int(11) NOT NULL default '0',
  `historic_role_code` int(11) NOT NULL default '0',
  `historic_user_name` varchar(200) NOT NULL default '',
  `historic_role_name` varchar(200) NOT NULL default '',
  PRIMARY KEY  (`historic_code`),
  KEY `relationship_120_fk` (`bulletin_code`),
  CONSTRAINT `fk_relationship_120` FOREIGN KEY (`bulletin_code`) REFERENCES `tb_bulletin` (`bulletin_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='almacena información de los envíos hechos por cada boletí';

#
# Structure for the `tb_layout_language` table : 
#

DROP TABLE IF EXISTS `tb_layout_language`;

CREATE TABLE `tb_layout_language` (
  `layout_lang_code` int(11) NOT NULL default '0',
  `language_code` int(11) NOT NULL default '0',
  `layout_code` int(11) NOT NULL default '0',
  `layout_lang_name` varchar(200) NOT NULL default '',
  PRIMARY KEY  (`layout_lang_code`),
  KEY `rel_layout_language_fk` (`layout_code`),
  KEY `rel_language_layout_fk` (`language_code`),
  CONSTRAINT `fk_rel_language_layout` FOREIGN KEY (`language_code`) REFERENCES `tb_language` (`language_code`),
  CONSTRAINT `fk_rel_layout_language` FOREIGN KEY (`layout_code`) REFERENCES `tb_layout` (`layout_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_menu_item` table : 
#

DROP TABLE IF EXISTS `tb_menu_item`;

CREATE TABLE `tb_menu_item` (
  `menu_item_code` int(11) NOT NULL default '0',
  `display_mode_code` int(11) NOT NULL default '0',
  `menu_lang_code` int(11) NOT NULL default '0',
  `content_code` int(11) NOT NULL default '0',
  `menu_item_parent` int(11) default NULL,
  `menu_item_name` varchar(200) default NULL,
  `menu_item_group` decimal(1,0) default NULL,
  `menu_item_level` int(11) NOT NULL default '0',
  `menu_item_order` int(11) NOT NULL default '0',
  `menu_item_enabled` decimal(1,0) NOT NULL default '0',
  `menu_item_width` int(11) default NULL,
  `menu_item_positionx` int(11) default NULL,
  `menu_item_positiony` int(11) default NULL,
  PRIMARY KEY  (`menu_item_code`),
  KEY `relationship_type_menu_fk` (`menu_lang_code`),
  KEY `rel_display_menu_fk` (`display_mode_code`),
  KEY `rel_content_manu_item_fk` (`content_code`),
  KEY `rel_parent_menu_item_fk` (`menu_item_parent`),
  CONSTRAINT `fk_relationship_type_menu` FOREIGN KEY (`menu_lang_code`) REFERENCES `tb_menu_language` (`menu_lang_code`),
  CONSTRAINT `fk_rel_content_manu_item` FOREIGN KEY (`content_code`) REFERENCES `tb_content` (`content_code`),
  CONSTRAINT `fk_rel_display_menu` FOREIGN KEY (`display_mode_code`) REFERENCES `tb_display_mode` (`display_mode_code`),
  CONSTRAINT `fk_rel_parent_menu_item` FOREIGN KEY (`menu_item_parent`) REFERENCES `tb_menu_item` (`menu_item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='almacena el primer item de menu a mostrarce en la zona';

#
# Structure for the `tb_news` table : 
#

DROP TABLE IF EXISTS `tb_news`;

CREATE TABLE `tb_news` (
  `news_code` int(11) NOT NULL default '0',
  `catalogue_code` int(11) NOT NULL default '0',
  `image_intro_code` int(11) default NULL,
  `image_main_code` int(11) default NULL,
  `news_title` varchar(200) NOT NULL default '',
  `news_leading_text` text,
  `news_text` text NOT NULL,
  `news_from` datetime default NULL,
  `news_to` datetime default NULL,
  `news_enabled` decimal(1,0) NOT NULL default '0',
  `news_publish_home` decimal(1,0) default NULL,
  `news_local_info` decimal(1,0) NOT NULL default '0',
  `news_creation` datetime NOT NULL default '0000-00-00 00:00:00',
  `news_index` decimal(10,0) default NULL,
  PRIMARY KEY  (`news_code`),
  KEY `rel_catalogue_news_fk` (`catalogue_code`),
  KEY `rel_image_news_intro_fk` (`image_intro_code`),
  KEY `rel_image_news_main_fk` (`image_main_code`),
  CONSTRAINT `fk_rel_catalogue_news` FOREIGN KEY (`catalogue_code`) REFERENCES `tb_catalogue` (`catalogue_code`),
  CONSTRAINT `fk_rel_image_news_intro` FOREIGN KEY (`image_intro_code`) REFERENCES `tb_image` (`image_code`),
  CONSTRAINT `fk_rel_image_news_main` FOREIGN KEY (`image_main_code`) REFERENCES `tb_image` (`image_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_news_document` table : 
#

DROP TABLE IF EXISTS `tb_news_document`;

CREATE TABLE `tb_news_document` (
  `news_code` int(11) NOT NULL default '0',
  `doc_container_code` int(11) NOT NULL default '0',
  PRIMARY KEY  (`news_code`,`doc_container_code`),
  KEY `news_document_fk` (`news_code`),
  KEY `news_document2_fk` (`doc_container_code`),
  CONSTRAINT `fk_news_document` FOREIGN KEY (`news_code`) REFERENCES `tb_news` (`news_code`),
  CONSTRAINT `fk_news_document2` FOREIGN KEY (`doc_container_code`) REFERENCES `tb_doc_container` (`doc_container_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_news_galery` table : 
#

DROP TABLE IF EXISTS `tb_news_galery`;

CREATE TABLE `tb_news_galery` (
  `image_galery_code` int(11) NOT NULL default '0',
  `news_code` int(11) NOT NULL default '0',
  PRIMARY KEY  (`image_galery_code`,`news_code`),
  KEY `news_galery_fk` (`image_galery_code`),
  KEY `news_galery2_fk` (`news_code`),
  CONSTRAINT `fk_news_galery` FOREIGN KEY (`image_galery_code`) REFERENCES `tb_image_galery` (`image_galery_code`),
  CONSTRAINT `fk_news_galery2` FOREIGN KEY (`news_code`) REFERENCES `tb_news` (`news_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_office_type` table : 
#

DROP TABLE IF EXISTS `tb_office_type`;

CREATE TABLE `tb_office_type` (
  `office_type_code` int(11) NOT NULL default '0',
  `office_type_name` varchar(200) default NULL,
  PRIMARY KEY  (`office_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tipo de oficina o sucursales de empresa\r\n';

#
# Structure for the `tb_office` table : 
#

DROP TABLE IF EXISTS `tb_office`;

CREATE TABLE `tb_office` (
  `office_code` int(11) NOT NULL default '0',
  `city_code` int(11) NOT NULL default '0',
  `office_type_code` int(11) NOT NULL default '0',
  `office_name` varchar(200) NOT NULL default '',
  `office_enabled` decimal(1,0) NOT NULL default '0',
  `office_address` varchar(250) NOT NULL default '',
  `office_image` varchar(255) default NULL,
  `office_phone` varchar(20) default NULL,
  `office_fax` varchar(20) default NULL,
  `office_email` varchar(200) default NULL,
  `office_schedule` text,
  `office_description` text,
  PRIMARY KEY  (`office_code`),
  KEY `relationship_131_fk` (`office_type_code`),
  KEY `relationship_132_fk` (`city_code`),
  CONSTRAINT `fk_relationship_131` FOREIGN KEY (`office_type_code`) REFERENCES `tb_office_type` (`office_type_code`),
  CONSTRAINT `fk_relationship_132` FOREIGN KEY (`city_code`) REFERENCES `tb_city` (`city_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='informacion de oficinas';

#
# Structure for the `tb_order_status` table : 
#

DROP TABLE IF EXISTS `tb_order_status`;

CREATE TABLE `tb_order_status` (
  `order_status_code` int(11) NOT NULL default '0',
  `order_status_name` varchar(200) NOT NULL default '',
  `order_status_notes` text,
  PRIMARY KEY  (`order_status_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='estado de la orden de pedido. \r\n1. -pendiente de cobro\r\n2.- ';

#
# Structure for the `tb_payment_type` table : 
#

DROP TABLE IF EXISTS `tb_payment_type`;

CREATE TABLE `tb_payment_type` (
  `payment_type_code` int(11) NOT NULL default '0',
  `payment_type_name` varchar(200) NOT NULL default '',
  `payment_type_enabled` decimal(1,0) NOT NULL default '0',
  `payment_type_restricted` decimal(1,0) NOT NULL default '0',
  `payment_type_discount` decimal(5,4) default NULL,
  `payment_type_external_code` varchar(20) default NULL,
  `payment_type_logo` varchar(255) default NULL,
  PRIMARY KEY  (`payment_type_code`),
  UNIQUE KEY `tb_payment_type_ak` (`payment_type_external_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tipo de pago registrado. diners, visa, mastercard, debito au';

#
# Structure for the `tb_payment_status` table : 
#

DROP TABLE IF EXISTS `tb_payment_status`;

CREATE TABLE `tb_payment_status` (
  `payment_status_code` int(11) NOT NULL default '0',
  `payment_status_name` varchar(200) NOT NULL default '',
  `payment_status_external_code` varchar(20) default NULL,
  `payment_status_message` text,
  PRIMARY KEY  (`payment_status_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='estado del pago efectuado a la orden de venta. \r\n1.- pendien';

#
# Structure for the `tb_order` table : 
#

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `order_code` int(11) NOT NULL default '0',
  `order_status_code` int(11) NOT NULL default '0',
  `payment_type_code` int(11) NOT NULL default '0',
  `customer_code` int(11) default NULL,
  `payment_status_code` int(11) NOT NULL default '0',
  `city_code` int(11) default NULL,
  `state_code` int(11) NOT NULL default '0',
  `delivery_agency_code` int(11) default NULL,
  `order_creation` datetime NOT NULL default '0000-00-00 00:00:00',
  `order_last_status` datetime NOT NULL default '0000-00-00 00:00:00',
  `order_last_payment_status` datetime NOT NULL default '0000-00-00 00:00:00',
  `order_enabled` decimal(1,0) NOT NULL default '0',
  `order_shipping_name` varchar(200) NOT NULL default '',
  `order_shipping_street1` varchar(250) NOT NULL default '',
  `order_shipping_street2` varchar(250) default NULL,
  `order_shipping_phone` varchar(20) default NULL,
  `order_shipping_reference` text,
  `order_before_discount` decimal(12,4) NOT NULL default '0.0000',
  `order_discount_amount` decimal(12,4) NOT NULL default '0.0000',
  `order_shipping_amount` decimal(12,4) NOT NULL default '0.0000',
  `order_shipping_tax` decimal(12,4) NOT NULL default '0.0000',
  `order_total_amount` decimal(12,4) NOT NULL default '0.0000',
  `order_bill_to` varchar(200) NOT NULL default '',
  `order_bill_id` varchar(20) NOT NULL default '',
  `order_bill_address` varchar(250) NOT NULL default '',
  `order_bill_phone` varchar(20) NOT NULL default '',
  `order_payment_request_date` datetime default NULL,
  `order_payment_success` datetime default NULL,
  `order_payment_reject` datetime default NULL,
  `order_payment_extra` varchar(255) default NULL,
  `order_payment_request_session_key` varchar(255) default NULL,
  `order_payment_response_session_key` varchar(255) default NULL,
  `order_payment_response_date` datetime default NULL,
  `order_payment_response_authorization` varchar(200) default NULL,
  `order_payment_response_error_code` varchar(200) default NULL,
  `order_payment_response_message` text,
  PRIMARY KEY  (`order_code`),
  KEY `orderstatus_order_fk` (`order_status_code`),
  KEY `relationship_95_fk` (`customer_code`),
  KEY `relationship_96_fk` (`payment_type_code`),
  KEY `relationship_97_fk` (`payment_status_code`),
  KEY `relationship_108_fk` (`city_code`),
  KEY `relationship_111_fk` (`delivery_agency_code`),
  KEY `relationship_112_fk` (`state_code`),
  CONSTRAINT `fk_orderstatus_order` FOREIGN KEY (`order_status_code`) REFERENCES `tb_order_status` (`order_status_code`),
  CONSTRAINT `fk_relationship_108` FOREIGN KEY (`city_code`) REFERENCES `tb_city` (`city_code`),
  CONSTRAINT `fk_relationship_111` FOREIGN KEY (`delivery_agency_code`) REFERENCES `tb_delivery_agency` (`delivery_agency_code`),
  CONSTRAINT `fk_relationship_112` FOREIGN KEY (`state_code`) REFERENCES `tb_state` (`state_code`),
  CONSTRAINT `fk_relationship_95` FOREIGN KEY (`customer_code`) REFERENCES `tb_customer` (`customer_code`),
  CONSTRAINT `fk_relationship_96` FOREIGN KEY (`payment_type_code`) REFERENCES `tb_payment_type` (`payment_type_code`),
  CONSTRAINT `fk_relationship_97` FOREIGN KEY (`payment_status_code`) REFERENCES `tb_payment_status` (`payment_status_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='pedido de entrega a domicilio';

#
# Structure for the `tb_sys_role` table : 
#

DROP TABLE IF EXISTS `tb_sys_role`;

CREATE TABLE `tb_sys_role` (
  `sys_role_code` int(11) NOT NULL default '0',
  `sys_role_parent` int(11) default NULL,
  `sys_role_level` decimal(4,0) NOT NULL default '0',
  `sys_role_name` varchar(50) NOT NULL default '',
  `sys_role_description` varchar(200) default NULL,
  PRIMARY KEY  (`sys_role_code`),
  KEY `relationship_37_fk` (`sys_role_parent`),
  CONSTRAINT `fk_relationship_37` FOREIGN KEY (`sys_role_parent`) REFERENCES `tb_sys_role` (`sys_role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_sys_access_mode` table : 
#

DROP TABLE IF EXISTS `tb_sys_access_mode`;

CREATE TABLE `tb_sys_access_mode` (
  `sys_access_mode_code` int(11) NOT NULL default '0',
  `sys_access_mode_name` varchar(50) NOT NULL default '',
  `sys_access_mode_enabled` decimal(1,0) default NULL,
  PRIMARY KEY  (`sys_access_mode_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='store the access modes to the system';

#
# Structure for the `tb_seller` table : 
#

DROP TABLE IF EXISTS `tb_seller`;

CREATE TABLE `tb_seller` (
  `seller_code` int(11) NOT NULL default '0',
  `seller_creation` datetime NOT NULL default '0000-00-00 00:00:00',
  `seller_name` varchar(200) NOT NULL default '',
  `seller_enabled` decimal(1,0) NOT NULL default '0',
  `seller_email` varchar(200) NOT NULL default '',
  `seller_ruc` varchar(15) NOT NULL default '',
  `seller_address` varchar(250) NOT NULL default '',
  `seller_legal_name` varchar(200) default NULL,
  `seller_contact_name` varchar(200) default NULL,
  `seller_summary` text,
  `seller_description` text,
  `seller_external_code` varchar(20) default NULL,
  `seller_logo` varchar(255) default NULL,
  `seller_phone1` varchar(20) default NULL,
  `seller_phone2` varchar(20) default NULL,
  `seller_fax` varchar(20) default NULL,
  `seller_image` varchar(255) default NULL,
  PRIMARY KEY  (`seller_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='compañias que vende productos a travez del sitio de comerci';

#
# Structure for the `tb_sys_user` table : 
#

DROP TABLE IF EXISTS `tb_sys_user`;

CREATE TABLE `tb_sys_user` (
  `sys_user_code` int(11) NOT NULL default '0',
  `sys_role_code` int(11) NOT NULL default '0',
  `sys_access_mode_code` int(11) default NULL,
  `seller_code` int(11) default NULL,
  `sys_user_name` varchar(20) NOT NULL default '',
  `sys_user_password` varchar(100) default NULL,
  `sys_user_enabled` decimal(1,0) NOT NULL default '0',
  `sys_user_application` decimal(1,0) NOT NULL default '0',
  `sys_user_email` varchar(200) default NULL,
  `sys_user_first_name` varchar(100) default NULL,
  `sys_user_last_name` varchar(100) default NULL,
  `sys_user_reset` decimal(1,0) default NULL,
  `sys_user_registration_date` date default NULL,
  PRIMARY KEY  (`sys_user_code`),
  UNIQUE KEY `ak_sys_user_name_ak` (`sys_user_name`),
  UNIQUE KEY `ak_sys_user_email_ak` (`sys_user_email`),
  KEY `relationship_34_fk` (`sys_role_code`),
  KEY `relationship_51_fk` (`sys_access_mode_code`),
  KEY `relationship_99_fk` (`seller_code`),
  CONSTRAINT `fk_relationship_34` FOREIGN KEY (`sys_role_code`) REFERENCES `tb_sys_role` (`sys_role_code`),
  CONSTRAINT `fk_relationship_51` FOREIGN KEY (`sys_access_mode_code`) REFERENCES `tb_sys_access_mode` (`sys_access_mode_code`),
  CONSTRAINT `fk_relationship_99` FOREIGN KEY (`seller_code`) REFERENCES `tb_seller` (`seller_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_order_status_detail` table : 
#

DROP TABLE IF EXISTS `tb_order_status_detail`;

CREATE TABLE `tb_order_status_detail` (
  `order_status_detail_code` int(11) NOT NULL default '0',
  `order_code` int(11) NOT NULL default '0',
  `payment_status_code` int(11) NOT NULL default '0',
  `sys_user_code` int(11) NOT NULL default '0',
  `order_status_detail_creation` datetime NOT NULL default '0000-00-00 00:00:00',
  `order_status_detail_remote_address` varchar(20) default NULL,
  `order_status_detail_remote_host` varchar(250) default NULL,
  PRIMARY KEY  (`order_status_detail_code`),
  KEY `relationship_104_fk` (`order_code`),
  KEY `relationship_105_fk` (`payment_status_code`),
  KEY `relationship_106_fk` (`sys_user_code`),
  CONSTRAINT `fk_relationship_104` FOREIGN KEY (`order_code`) REFERENCES `tb_order` (`order_code`),
  CONSTRAINT `fk_relationship_105` FOREIGN KEY (`payment_status_code`) REFERENCES `tb_payment_status` (`payment_status_code`),
  CONSTRAINT `fk_relationship_106` FOREIGN KEY (`sys_user_code`) REFERENCES `tb_sys_user` (`sys_user_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='evolución de los estados de una orden de pedido.';

#
# Structure for the `tb_page_log` table : 
#

DROP TABLE IF EXISTS `tb_page_log`;

CREATE TABLE `tb_page_log` (
  `page_log_code` int(11) NOT NULL default '0',
  `page_log_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `page_log_session_id` varchar(200) NOT NULL default '',
  `page_log_url` varchar(255) NOT NULL default '',
  `page_log_remote_address` varchar(255) NOT NULL default '',
  `page_log_remote_host` varchar(255) NOT NULL default '',
  `page_log_resource_code` int(11) NOT NULL default '0',
  `page_log_resource_description` varchar(200) NOT NULL default '',
  `page_log_resource_type` varchar(200) NOT NULL default '',
  `page_log_user_agent` varchar(200) default NULL,
  `page_log_referrer` varchar(200) default NULL,
  `page_log_language` varchar(200) default NULL,
  PRIMARY KEY  (`page_log_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='página de registro de visitas al portal web';

#
# Structure for the `tb_pax_type` table : 
#

DROP TABLE IF EXISTS `tb_pax_type`;

CREATE TABLE `tb_pax_type` (
  `pax_type_code` int(11) NOT NULL default '0',
  `pax_type_name` varchar(200) NOT NULL default '',
  PRIMARY KEY  (`pax_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tipo de pasajero para cuadros tarifarios ( no administrable)';

#
# Structure for the `tb_position` table : 
#

DROP TABLE IF EXISTS `tb_position`;

CREATE TABLE `tb_position` (
  `position_code` int(11) NOT NULL default '0',
  `area_code` int(11) NOT NULL default '0',
  `position_name` varchar(200) NOT NULL default '',
  `position_short_description` text NOT NULL,
  `position_long_description` text,
  `position_enabled` decimal(1,0) NOT NULL default '0',
  `position_profile_description` text,
  PRIMARY KEY  (`position_code`),
  KEY `rel_area_position_fk` (`area_code`),
  CONSTRAINT `fk_rel_area_position` FOREIGN KEY (`area_code`) REFERENCES `tb_area` (`area_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_requester` table : 
#

DROP TABLE IF EXISTS `tb_requester`;

CREATE TABLE `tb_requester` (
  `requester_code` int(11) NOT NULL default '0',
  `country_code` int(11) default NULL,
  `instruction_level_code` int(11) default NULL,
  `study_branch_code` int(11) default NULL,
  `salary_aspiration_code` int(11) default NULL,
  `city_code` int(11) default NULL,
  `marital_status_code` int(11) default NULL,
  `requester_first_name` varchar(200) NOT NULL default '',
  `requester_last_name` varchar(200) NOT NULL default '',
  `requester_second_first_name` varchar(200) default NULL,
  `requester_second_last_name` varchar(200) default NULL,
  `requester_address` varchar(250) NOT NULL default '',
  `requester_phone` varchar(20) NOT NULL default '',
  `requester_aplication_date` datetime default NULL,
  `requester_vitae` varchar(255) default NULL,
  `requester_gender` decimal(1,0) default NULL,
  `requester_working` decimal(1,0) default NULL,
  `requester_identification` varchar(150) default NULL,
  `requester_office_phone` varchar(20) default NULL,
  `requester_mobile` varchar(20) default NULL,
  `requester_email` varchar(200) default NULL,
  `requester_birth_date` date default NULL,
  `requester_vehicle` decimal(1,0) default NULL,
  `requester_travel_possible` decimal(1,0) default NULL,
  `requester_enterprise_name` varchar(200) default NULL,
  `requester_enterprise_city` varchar(200) default NULL,
  `requester_brief_application` decimal(1,0) NOT NULL default '0',
  `requester_instruction_addinfo` text,
  `requester_city` varchar(200) default NULL,
  PRIMARY KEY  (`requester_code`),
  KEY `rel_city_requester_fk` (`city_code`),
  KEY `rel_marital_status_fk` (`marital_status_code`),
  KEY `rel_instruction_level_fk` (`salary_aspiration_code`),
  KEY `rel_study_brach_fk` (`study_branch_code`),
  KEY `rel_salary_aspiration_fk` (`instruction_level_code`),
  KEY `rel_requester_country_fk` (`country_code`),
  CONSTRAINT `fk_rel_city_requester` FOREIGN KEY (`city_code`) REFERENCES `tb_city` (`city_code`),
  CONSTRAINT `fk_rel_instruction_level` FOREIGN KEY (`salary_aspiration_code`) REFERENCES `tb_catalogue` (`catalogue_code`),
  CONSTRAINT `fk_rel_marital_status` FOREIGN KEY (`marital_status_code`) REFERENCES `tb_catalogue` (`catalogue_code`),
  CONSTRAINT `fk_rel_requester_country` FOREIGN KEY (`country_code`) REFERENCES `tb_country` (`country_code`),
  CONSTRAINT `fk_rel_salary_aspiration` FOREIGN KEY (`instruction_level_code`) REFERENCES `tb_catalogue` (`catalogue_code`),
  CONSTRAINT `fk_rel_study_brach` FOREIGN KEY (`study_branch_code`) REFERENCES `tb_catalogue` (`catalogue_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_position_requested` table : 
#

DROP TABLE IF EXISTS `tb_position_requested`;

CREATE TABLE `tb_position_requested` (
  `position_requested_code` int(11) NOT NULL default '0',
  `position_code` int(11) NOT NULL default '0',
  `requester_code` int(11) NOT NULL default '0',
  `position_requested_experience` decimal(1,0) default NULL,
  PRIMARY KEY  (`position_requested_code`),
  KEY `rel_requester_position_requester_fk` (`requester_code`),
  KEY `rel_position_position_requester_fk` (`position_code`),
  CONSTRAINT `fk_rel_position_position_requester` FOREIGN KEY (`position_code`) REFERENCES `tb_position` (`position_code`),
  CONSTRAINT `fk_rel_requester_position_requester` FOREIGN KEY (`requester_code`) REFERENCES `tb_requester` (`requester_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_tax_type` table : 
#

DROP TABLE IF EXISTS `tb_tax_type`;

CREATE TABLE `tb_tax_type` (
  `tax_type_code` int(11) NOT NULL default '0',
  `tax_type_name` varchar(200) default NULL,
  PRIMARY KEY  (`tax_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tipos de impuestos aplicacbles a un producto en particular.';

#
# Structure for the `tb_product` table : 
#

DROP TABLE IF EXISTS `tb_product`;

CREATE TABLE `tb_product` (
  `product_code` int(11) NOT NULL default '0',
  `seller_code` int(11) NOT NULL default '0',
  `tax_type_code` int(11) NOT NULL default '0',
  `brand_code` int(11) default NULL,
  `category_code` int(11) NOT NULL default '0',
  `product_enabled` decimal(1,0) NOT NULL default '0',
  `product_featured` decimal(1,0) NOT NULL default '0',
  `product_show_room` decimal(1,0) NOT NULL default '0',
  `product_name` varchar(200) NOT NULL default '',
  `product_creation` datetime NOT NULL default '0000-00-00 00:00:00',
  `product_last_update` datetime NOT NULL default '0000-00-00 00:00:00',
  `product_stock` int(11) NOT NULL default '0',
  `product_min` int(11) NOT NULL default '0',
  `product_regular_price` decimal(12,4) NOT NULL default '0.0000',
  `product_on_clearance` decimal(1,0) NOT NULL default '0',
  `product_on_sale` decimal(1,0) NOT NULL default '0',
  `product_features` text NOT NULL,
  `product_description` text,
  `product_slogan` varchar(255) default NULL,
  `product_max` int(11) default NULL,
  `product_sale_price` decimal(12,4) default NULL,
  `product_clearance_price` decimal(12,4) default NULL,
  `product_image1` varchar(255) default NULL,
  `product_image2` varchar(255) default NULL,
  `product_image3` varchar(255) default NULL,
  `product_quota_num` int(11) default NULL,
  `product_quota_price` decimal(12,4) default NULL,
  `product_shipping_width` decimal(12,4) default NULL,
  `product_shipping_height` decimal(12,4) default NULL,
  `product_shipping_depth` decimal(12,4) default NULL,
  `product_shipping_weight` decimal(12,4) default NULL,
  `product_model_number` varchar(20) default NULL,
  `product_external_code` varchar(20) default NULL,
  `product_clearance_since` datetime default NULL,
  `product_clearance_until` datetime default NULL,
  PRIMARY KEY  (`product_code`),
  KEY `relationship_86_fk` (`category_code`),
  KEY `relationship_87_fk` (`brand_code`),
  KEY `relationship_88_fk` (`seller_code`),
  KEY `relationship_118_fk` (`tax_type_code`),
  CONSTRAINT `fk_relationship_117` FOREIGN KEY (`tax_type_code`) REFERENCES `tb_tax_type` (`tax_type_code`),
  CONSTRAINT `fk_relationship_86` FOREIGN KEY (`category_code`) REFERENCES `tb_category` (`category_code`),
  CONSTRAINT `fk_relationship_87` FOREIGN KEY (`brand_code`) REFERENCES `tb_brand` (`brand_code`),
  CONSTRAINT `fk_relationship_88` FOREIGN KEY (`seller_code`) REFERENCES `tb_seller` (`seller_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='infomación de productos a ser comercializados desde el siti';

#
# Structure for the `tb_products_related` table : 
#

DROP TABLE IF EXISTS `tb_products_related`;

CREATE TABLE `tb_products_related` (
  `product_parent` int(11) NOT NULL default '0',
  `product_code` int(11) NOT NULL default '0',
  PRIMARY KEY  (`product_parent`,`product_code`),
  KEY `relationship_89_fk` (`product_code`),
  KEY `relationship_90_fk` (`product_parent`),
  CONSTRAINT `fk_product` FOREIGN KEY (`product_code`) REFERENCES `tb_product` (`product_code`),
  CONSTRAINT `fk_related` FOREIGN KEY (`product_parent`) REFERENCES `tb_product` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_related_content` table : 
#

DROP TABLE IF EXISTS `tb_related_content`;

CREATE TABLE `tb_related_content` (
  `related_content_code` int(11) NOT NULL default '0',
  `content_code` int(11) NOT NULL default '0',
  `content_related` int(11) NOT NULL default '0',
  `related_content_name` varchar(200) NOT NULL default '',
  `related_content_enabled` decimal(1,0) NOT NULL default '0',
  PRIMARY KEY  (`related_content_code`),
  KEY `rel_content_leader_fk` (`content_related`),
  KEY `rel_content_follow_fk` (`content_code`),
  CONSTRAINT `fk_rel_content_follow` FOREIGN KEY (`content_code`) REFERENCES `tb_content` (`content_code`),
  CONSTRAINT `fk_rel_content_leader` FOREIGN KEY (`content_related`) REFERENCES `tb_content` (`content_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_requester_work_city` table : 
#

DROP TABLE IF EXISTS `tb_requester_work_city`;

CREATE TABLE `tb_requester_work_city` (
  `requester_work_city_code` int(11) NOT NULL default '0',
  `requester_code` int(11) NOT NULL default '0',
  `city_code` int(11) NOT NULL default '0',
  PRIMARY KEY  (`requester_work_city_code`),
  KEY `rel_requester_work_city_fk` (`requester_code`),
  KEY `rel_work_city_requester_fk` (`city_code`),
  CONSTRAINT `fk_rel_requester_work_city` FOREIGN KEY (`requester_code`) REFERENCES `tb_requester` (`requester_code`),
  CONSTRAINT `fk_rel_work_city_requester` FOREIGN KEY (`city_code`) REFERENCES `tb_city` (`city_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_sequence` table : 
#

DROP TABLE IF EXISTS `tb_sequence`;

CREATE TABLE `tb_sequence` (
  `name` varchar(200) NOT NULL default '',
  `next_val` int(11) default '0',
  PRIMARY KEY  (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_subscriber` table : 
#

DROP TABLE IF EXISTS `tb_subscriber`;

CREATE TABLE `tb_subscriber` (
  `subscriber_code` int(11) NOT NULL default '0',
  `country_code` int(11) NOT NULL default '0',
  `subscriber_firstname` varchar(200) NOT NULL default '',
  `subscriber_lastname` varchar(200) NOT NULL default '',
  `subscriber_occupation` varchar(200) default NULL,
  `subscriber_city` varchar(200) NOT NULL default '',
  `subscriber_state` varchar(200) default NULL,
  `subscriber_phone_country_code` varchar(10) default NULL,
  `subscriber_phone_city_code` varchar(10) default NULL,
  `subscriber_phone` varchar(10) default NULL,
  `subscriber_email` varchar(200) NOT NULL default '',
  `subscriber_issubscribed` decimal(1,0) NOT NULL default '0',
  `subscriber_address` varchar(200) default NULL,
  `subscriber_system_date` date NOT NULL default '0000-00-00',
  `subscriber_enabled` decimal(1,0) NOT NULL default '0',
  PRIMARY KEY  (`subscriber_code`),
  KEY `ak_uk_subscriber_email` (`subscriber_email`),
  KEY `relationship_119_fk` (`country_code`),
  CONSTRAINT `fk_relationship_119` FOREIGN KEY (`country_code`) REFERENCES `tb_country` (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_subscriber_topic` table : 
#

DROP TABLE IF EXISTS `tb_subscriber_topic`;

CREATE TABLE `tb_subscriber_topic` (
  `subscriber_topic_code` int(11) NOT NULL default '0',
  `topic_code` int(11) NOT NULL default '0',
  `subscriber_code` int(11) NOT NULL default '0',
  PRIMARY KEY  (`subscriber_topic_code`),
  KEY `rel_topic_subscribertopic_fk` (`topic_code`),
  KEY `rel_subscriber_substopic_fk` (`subscriber_code`),
  CONSTRAINT `fk_rel_subscriber_substopic` FOREIGN KEY (`subscriber_code`) REFERENCES `tb_subscriber` (`subscriber_code`),
  CONSTRAINT `fk_rel_topic_subscribertopic` FOREIGN KEY (`topic_code`) REFERENCES `tb_topic` (`topic_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_sys_ldap_user` table : 
#

DROP TABLE IF EXISTS `tb_sys_ldap_user`;

CREATE TABLE `tb_sys_ldap_user` (
  `sys_ldap_user_code` int(11) NOT NULL default '0',
  `sys_user_code` int(11) default NULL,
  `sys_ldap_user_registered` decimal(1,0) NOT NULL default '0',
  `sys_ldap_username` varchar(200) NOT NULL default '',
  PRIMARY KEY  (`sys_ldap_user_code`),
  KEY `rel_sys_user_ldap_fk` (`sys_user_code`),
  CONSTRAINT `fk_rel_sys_user_ldap` FOREIGN KEY (`sys_user_code`) REFERENCES `tb_sys_user` (`sys_user_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_sys_module` table : 
#

DROP TABLE IF EXISTS `tb_sys_module`;

CREATE TABLE `tb_sys_module` (
  `sys_module_code` int(11) NOT NULL default '0',
  `sys_module_parent` int(11) default NULL,
  `sys_access_mode_code` int(11) default NULL,
  `sys_module_level` decimal(4,0) NOT NULL default '0',
  `sys_module_group` decimal(1,0) NOT NULL default '0',
  `sys_module_name` varchar(100) NOT NULL default '',
  `sys_module_key` varchar(150) NOT NULL default '',
  `sys_module_url` varchar(255) default NULL,
  `sys_module_order_index` decimal(4,0) default NULL,
  `sys_module_x` decimal(4,0) default '0',
  `sys_module_y` decimal(4,0) default '0',
  `sys_module_show_panel` decimal(1,0) default NULL,
  PRIMARY KEY  (`sys_module_code`),
  KEY `relationship_38_fk` (`sys_module_parent`),
  KEY `relationship_52_fk` (`sys_access_mode_code`),
  CONSTRAINT `fk_relationship_38` FOREIGN KEY (`sys_module_parent`) REFERENCES `tb_sys_module` (`sys_module_code`),
  CONSTRAINT `fk_relationship_52` FOREIGN KEY (`sys_access_mode_code`) REFERENCES `tb_sys_access_mode` (`sys_access_mode_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_sys_params` table : 
#

DROP TABLE IF EXISTS `tb_sys_params`;

CREATE TABLE `tb_sys_params` (
  `sys_params_code` int(11) NOT NULL default '0',
  `sys_params_name` varchar(200) NOT NULL default '',
  `sys_params_value` varchar(200) NOT NULL default '',
  `sys_params_type` varchar(200) default NULL,
  PRIMARY KEY  (`sys_params_code`),
  KEY `ak_ak_parameter_name` (`sys_params_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='parametros globales del sistema. nombre del parametro valor ';

#
# Structure for the `tb_sys_profile` table : 
#

DROP TABLE IF EXISTS `tb_sys_profile`;

CREATE TABLE `tb_sys_profile` (
  `sys_profile_code` int(11) NOT NULL default '0',
  `sys_role_code` int(11) NOT NULL default '0',
  `sys_module_code` int(11) NOT NULL default '0',
  PRIMARY KEY  (`sys_profile_code`),
  KEY `relationship_35_fk` (`sys_role_code`),
  KEY `relationship_36_fk` (`sys_module_code`),
  CONSTRAINT `fk_relationship_35` FOREIGN KEY (`sys_role_code`) REFERENCES `tb_sys_role` (`sys_role_code`),
  CONSTRAINT `fk_relationship_36` FOREIGN KEY (`sys_module_code`) REFERENCES `tb_sys_module` (`sys_module_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_tax` table : 
#

DROP TABLE IF EXISTS `tb_tax`;

CREATE TABLE `tb_tax` (
  `tax_code` int(11) NOT NULL default '0',
  `tax_name` varchar(200) NOT NULL default '',
  PRIMARY KEY  (`tax_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tabla en la cual se registran impuestos vigentes con los que';

#
# Structure for the `tb_tax_applied` table : 
#

DROP TABLE IF EXISTS `tb_tax_applied`;

CREATE TABLE `tb_tax_applied` (
  `tax_type_code` int(11) NOT NULL default '0',
  `tax_code` int(11) NOT NULL default '0',
  PRIMARY KEY  (`tax_type_code`,`tax_code`),
  KEY `relationship_116_fk` (`tax_type_code`),
  KEY `relationship_117_fk` (`tax_code`),
  CONSTRAINT `fk_tax_applied` FOREIGN KEY (`tax_type_code`) REFERENCES `tb_tax_type` (`tax_type_code`),
  CONSTRAINT `fk_tax_applied2` FOREIGN KEY (`tax_code`) REFERENCES `tb_tax` (`tax_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `tb_tax_detail` table : 
#

DROP TABLE IF EXISTS `tb_tax_detail`;

CREATE TABLE `tb_tax_detail` (
  `tax_detail_code` int(11) NOT NULL default '0',
  `tax_code` int(11) NOT NULL default '0',
  `order_code` int(11) NOT NULL default '0',
  `tax_detail_name` varchar(200) NOT NULL default '',
  `taxt_detail_amount` decimal(12,4) NOT NULL default '0.0000',
  `tax_detail_rate` decimal(5,4) NOT NULL default '0.0000',
  PRIMARY KEY  (`tax_detail_code`),
  KEY `relationship_122_fk` (`tax_code`),
  KEY `relationship_123_fk` (`order_code`),
  CONSTRAINT `fk_relationship_121` FOREIGN KEY (`tax_code`) REFERENCES `tb_tax` (`tax_code`),
  CONSTRAINT `fk_relationship_122` FOREIGN KEY (`order_code`) REFERENCES `tb_order` (`order_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='detalle de valores pagados por impuestos en las compras de l';

#
# Structure for the `tb_tax_rate` table : 
#

DROP TABLE IF EXISTS `tb_tax_rate`;

CREATE TABLE `tb_tax_rate` (
  `tax_rate_code` int(11) NOT NULL default '0',
  `tax_code` int(11) NOT NULL default '0',
  `tax_rate_value` decimal(12,4) NOT NULL default '0.0000',
  `tax_rate_since` datetime NOT NULL default '0000-00-00 00:00:00',
  `tax_rate_until` datetime default NULL,
  PRIMARY KEY  (`tax_rate_code`),
  KEY `relationship_123_fk` (`tax_code`),
  CONSTRAINT `fk_relationship_123` FOREIGN KEY (`tax_code`) REFERENCES `tb_tax` (`tax_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='porcentages de los impuestos con fecha desde y hasta.';

#
# Structure for the `tb_ticket_rate` table : 
#

DROP TABLE IF EXISTS `tb_ticket_rate`;

CREATE TABLE `tb_ticket_rate` (
  `ticket_rate_code` int(11) NOT NULL default '0',
  `route_code` int(11) NOT NULL default '0',
  `pax_type_code` int(11) NOT NULL default '0',
  `ticket_rate_enabled` decimal(1,0) NOT NULL default '0',
  `ticket_rate_resident_fare` decimal(1,0) NOT NULL default '0',
  `ticket_rate_fare` decimal(12,4) NOT NULL default '0.0000',
  `ticket_rate_iva_rate` decimal(5,4) NOT NULL default '0.0000',
  `ticket_rate_tax1` decimal(12,4) NOT NULL default '0.0000',
  `ticket_rate_tax2` decimal(12,4) NOT NULL default '0.0000',
  `ticket_rate_apply_tax3` decimal(1,0) default NULL,
  `ticket_rate_tax3` decimal(12,4) default NULL,
  PRIMARY KEY  (`ticket_rate_code`),
  KEY `relationship_137_fk` (`route_code`),
  KEY `relationship_138_fk` (`pax_type_code`),
  CONSTRAINT `fk_relationship_137` FOREIGN KEY (`route_code`) REFERENCES `tb_route` (`route_code`),
  CONSTRAINT `fk_relationship_138` FOREIGN KEY (`pax_type_code`) REFERENCES `tb_pax_type` (`pax_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tarifas aereas por tipo de pasajero';

#
# Structure for the `tb_zone` table : 
#

DROP TABLE IF EXISTS `tb_zone`;

CREATE TABLE `tb_zone` (
  `zone_code` int(11) NOT NULL default '0',
  `city_code` int(11) NOT NULL default '0',
  `zone_name` varchar(200) default NULL,
  `zone_enabled` decimal(1,0) default NULL,
  PRIMARY KEY  (`zone_code`),
  KEY `relationship_70_fk` (`city_code`),
  CONSTRAINT `fk_relationship_70` FOREIGN KEY (`city_code`) REFERENCES `tb_city` (`city_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `tb_access_url` table  (LIMIT 0,500)
#

INSERT INTO `tb_access_url` (`access_url_code`, `access_url_name`, `access_url_path`, `access_url_class_name`) VALUES 
  (1,'external URL','',''),
  (2,'Contenido','/portal/main.do?code=','com.iportal.model.portal.Content'),
  (3,'Noticia','/portal/news.do?action=read&itemCode=1&code=','com.iportal.cart.model.news.News');

COMMIT;

#
# Data for the `tb_access` table  (LIMIT 0,500)
#

INSERT INTO `tb_access` (`access_code`, `access_url_code`, `access_name`, `access_url`, `access_path`, `access_height`, `access_width`, `access_related_code`, `access_related_title`, `access_desc`) VALUES 
  (240,2,'Icaro','/portal/main.do?code=','/images/uploaded/access/access-000025.jpg',NULL,NULL,30,'Icaro',NULL),
  (260,2,'Compras en Línea','/portal/main.do?code=','/images/uploaded/access/access-000018.gif',NULL,NULL,35,'Compras en línea',NULL),
  (261,2,'Servicios','/portal/main.do?code=','/images/uploaded/access/access-000024.jpg',NULL,NULL,36,'Servicios',NULL),
  (262,2,'Socios Icaro','/portal/main.do?code=','/images/uploaded/access/access-000023.jpg',NULL,NULL,37,'Socios Icaro',NULL),
  (263,1,'Niño Esperanza','http://www.ninoesperanza.com/','/images/uploaded/access/access-000021.gif',NULL,NULL,NULL,NULL,NULL),
  (264,1,'Aldeas','http://www.ninoesperanza.com/','/images/uploaded/access/access-000022.jpg',NULL,NULL,NULL,NULL,NULL);

COMMIT;

#
# Data for the `tb_access_level` table  (LIMIT 0,500)
#

INSERT INTO `tb_access_level` (`access_level_code`, `access_level_name`, `access_level_key`, `access_level_enabled`) VALUES 
  (1,'Público','accessLevel.public',1),
  (2,'Registrado','accessLevel.registered',1);

COMMIT;

#
# Data for the `tb_language` table  (LIMIT 0,500)
#

INSERT INTO `tb_language` (`language_code`, `language_default`, `language_enabled`, `language_name`, `language_locale`, `language_integer_format`, `language_float_format`, `language_currency_format`, `language_date_format`, `language_time_format`, `language_date_time_format`, `language_short_date_format`, `language_long_date_format`, `language_charset`) VALUES 
  (1,1,1,'Español','es','#,##0','#,##0.00','#,##0.00','yyyy-MM-dd','HH:mm:ss','yyyy-MM-dd HH:mm:ss','EE, MMM dd, yyyy','EEEE, MMMM dd, yyyy','iso-8859-1'),
  (2,0,0,'English','en','#,##0','#,##0.00','#,##0.00','dd-MM-yyyy','HH:mm:ss','dd-MM-yyyy HH:mm:ss','EE, MMM dd, yyyy','EEEE, MMMM dd, yyyy','iso-8859-1');

COMMIT;

#
# Data for the `tb_menu` table  (LIMIT 0,500)
#

INSERT INTO `tb_menu` (`menu_code`, `menu_enabled`, `menu_level`) VALUES 
  (1,1,1),
  (2,1,1),
  (3,1,1),
  (4,1,1),
  (5,1,1),
  (6,1,1);

COMMIT;

#
# Data for the `tb_menu_language` table  (LIMIT 0,500)
#

INSERT INTO `tb_menu_language` (`menu_lang_code`, `language_code`, `menu_code`, `menu_lang_name`) VALUES 
  (1,1,1,'Menú Principal (izquierda)'),
  (2,1,2,'Menú de Herramientas (superior)'),
  (3,1,3,'Formas de Pago'),
  (4,1,4,'Menú Secundario (inferior)'),
  (5,1,5,'Menu Banners Home (inferior)'),
  (6,1,6,'Menú Banners Home (derecha)');

COMMIT;

#
# Data for the `tb_display_mode` table  (LIMIT 0,500)
#

INSERT INTO `tb_display_mode` (`display_mode_code`, `display_mode_name`, `display_mode_key`, `display_mode_enabled`) VALUES 
  (1,'Texto','displayMode.text',1),
  (2,'Banner','displayMode.banner',1),
  (3,'Resumen','displayMode.brief',1);

COMMIT;

#
# Data for the `tb_access_menu` table  (LIMIT 0,500)
#

INSERT INTO `tb_access_menu` (`access_menu_code`, `menu_lang_code`, `access_code`, `display_mode_code`, `access_menu_order`, `access_menu_enabled`, `access_menu_width`, `access_menu_positionx`, `access_menu_positiony`) VALUES 
  (260,1,240,1,1,1,NULL,NULL,NULL),
  (280,1,260,1,2,1,NULL,NULL,NULL),
  (281,1,261,2,3,1,NULL,NULL,NULL),
  (282,1,262,2,4,1,NULL,NULL,NULL),
  (283,5,263,2,1,1,NULL,NULL,NULL),
  (284,5,264,2,2,1,NULL,NULL,NULL),
  (300,6,240,3,1,1,NULL,NULL,NULL),
  (301,6,261,2,2,1,NULL,NULL,NULL),
  (302,6,262,3,3,1,NULL,NULL,NULL);

COMMIT;

#
# Data for the `tb_country` table  (LIMIT 0,500)
#

INSERT INTO `tb_country` (`country_code`, `country_name`, `country_iso`, `country_key`, `country_enabled`, `country_home_delivery`) VALUES 
  (1,'Ecuador','ECU','country.ecuador',1,1),
  (2,'Colombia','COL','',1,0);

COMMIT;

#
# Data for the `tb_state` table  (LIMIT 0,500)
#

INSERT INTO `tb_state` (`state_code`, `country_code`, `state_name`, `state_key`, `state_enabled`) VALUES 
  (1,1,'Pichincha',NULL,1),
  (2,1,'Imbabura',NULL,1),
  (4,1,'Carchi',NULL,1),
  (5,1,'Esmeraldas',NULL,1),
  (6,1,'Napo',NULL,1),
  (7,1,'Orellana',NULL,1),
  (8,1,'Sucumbios',NULL,1),
  (9,1,'Guayas',NULL,1),
  (10,1,'Los Ríos',NULL,1),
  (11,1,'Galápagos',NULL,1),
  (12,1,'Tungurahua',NULL,1),
  (13,1,'Pastaza',NULL,1),
  (14,1,'Cotopaxi',NULL,1),
  (15,1,'Chimborazo',NULL,1),
  (16,1,'Bolívar',NULL,1),
  (17,1,'Azuay',NULL,1),
  (18,1,'Cañar',NULL,1),
  (19,1,'Morona Santiago',NULL,1),
  (20,1,'Loja',NULL,1),
  (21,1,'Zamora Chinchipe',NULL,1),
  (22,1,'Manabí',NULL,1),
  (23,1,'El Oro',NULL,1),
  (480,2,'Nariño','',1);

COMMIT;

#
# Data for the `tb_city` table  (LIMIT 0,500)
#

INSERT INTO `tb_city` (`city_code`, `state_code`, `city_name`, `city_enabled`, `city_home_delivery`, `city_key`) VALUES 
  (2,2,'Ibarra',1,1,''),
  (3,5,'Esmeraldas',1,0,NULL),
  (4,9,'Guayaquil',1,1,NULL),
  (5,10,'Babahoyo',1,0,NULL),
  (6,10,'Quevedo',1,0,NULL),
  (7,11,'Pto.Baquerizo Moreno',1,0,NULL),
  (8,11,'Pto.Ayora',1,0,NULL),
  (9,11,'San Cristobal',1,0,NULL),
  (10,12,'Ambato',1,1,''),
  (11,12,'Baños',1,0,NULL),
  (12,13,'Puyo',1,0,NULL),
  (13,14,'Latacunga',1,0,NULL),
  (14,15,'Riobamba',1,1,NULL),
  (15,16,'Guaranda',1,0,NULL),
  (16,17,'Cuenca',1,1,NULL),
  (17,18,'Azogues',1,0,NULL),
  (18,19,'Macas',1,0,NULL),
  (19,20,'Loja',1,1,NULL),
  (20,21,'Zamora',1,0,NULL),
  (21,22,'Portoviejo',1,1,NULL),
  (22,22,'Manta',1,0,NULL),
  (23,22,'Chone',1,0,NULL),
  (24,22,'Bahía de Caráquez',1,0,NULL),
  (25,22,'Jipijapa',1,0,NULL),
  (26,23,'Machala',1,1,NULL),
  (27,23,'Piñas',1,0,NULL),
  (28,1,'Santo Domingo',1,1,NULL),
  (29,4,'Tulcán',1,0,NULL),
  (30,6,'Tena',1,0,NULL),
  (31,8,'Nueva Loja',1,0,NULL),
  (32,1,'Quito',1,1,NULL),
  (33,7,'Orellana',1,0,NULL),
  (34,9,'La Libertad',1,0,NULL),
  (840,15,'Guano',1,0,'');

COMMIT;

#
# Data for the `tb_airport` table  (LIMIT 0,500)
#

INSERT INTO `tb_airport` (`airport_code`, `city_code`, `airport_name`, `airport_iata_code`, `airport_enabled`) VALUES 
  (9,32,'Mariscal Sucre','12345',1),
  (10,4,'Simón Bolivar','67890',1),
  (11,4,'Jose Joaquín de Olmedo','00000',1);

COMMIT;

#
# Data for the `tb_log` table  (LIMIT 0,500)
#

INSERT INTO `tb_log` (`log_code`, `log_user_code`, `log_user_name`, `log_role_code`, `log_role_name`, `log_in_date`, `log_out_date`) VALUES 
  (12720,1,'system',1,'SYSTEM','2007-06-15',NULL),
  (12721,1,'system',1,'SYSTEM','2007-06-15','2007-06-15'),
  (12740,1,'system',1,'SYSTEM','2007-06-18',NULL),
  (12741,1,'system',1,'SYSTEM','2007-06-18','2007-06-18'),
  (12760,1,'system',1,'SYSTEM','2007-06-18',NULL),
  (12761,1,'system',1,'SYSTEM','2007-06-18','2007-06-18'),
  (12780,1,'system',1,'SYSTEM','2007-06-19',NULL),
  (12781,1,'system',1,'SYSTEM','2007-06-19','2007-06-19'),
  (12800,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (12801,1,'system',1,'SYSTEM','2007-06-26','2007-06-26'),
  (12802,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (12803,1,'system',1,'SYSTEM','2007-06-26','2007-06-26'),
  (12804,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (12820,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (12821,1,'system',1,'SYSTEM','2007-06-26','2007-06-26'),
  (12822,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (12823,1,'system',1,'SYSTEM','2007-06-26','2007-06-26'),
  (12824,140,'test',2,'Administrador','2007-06-26',NULL),
  (12825,140,'test',2,'Administrador','2007-06-26','2007-06-26'),
  (12840,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (12841,1,'system',1,'SYSTEM','2007-06-26','2007-06-26'),
  (12842,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (12860,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (12880,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (12900,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (12920,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (12921,1,'system',1,'SYSTEM','2007-06-26','2007-06-26'),
  (12922,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (12940,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (12960,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (12980,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (13000,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (13020,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (13021,1,'system',1,'SYSTEM','2007-06-26','2007-06-26'),
  (13022,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (13040,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (13041,1,'system',1,'SYSTEM','2007-06-26','2007-06-26'),
  (13042,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (13060,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (13080,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (13100,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (13120,1,'system',1,'SYSTEM','2007-06-26',NULL),
  (13140,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13160,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13180,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13200,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13201,1,'system',1,'SYSTEM','2007-06-27','2007-06-27'),
  (13220,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13221,1,'system',1,'SYSTEM','2007-06-27','2007-06-27'),
  (13240,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13260,140,'test',2,'Administrador','2007-06-27',NULL),
  (13261,140,'test',2,'Administrador','2007-06-27','2007-06-27'),
  (13262,140,'test',2,'Administrador','2007-06-27',NULL),
  (13263,140,'test',2,'Administrador','2007-06-27','2007-06-27'),
  (13280,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13300,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13301,1,'system',1,'SYSTEM','2007-06-27','2007-06-27'),
  (13302,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13320,140,'test',2,'Administrador','2007-06-27',NULL),
  (13321,140,'test',2,'Administrador','2007-06-27',NULL),
  (13322,140,'test',2,'Administrador','2007-06-27','2007-06-27'),
  (13323,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13340,140,'test',2,'Administrador','2007-06-27',NULL),
  (13341,140,'test',2,'Administrador','2007-06-27','2007-06-27'),
  (13342,140,'test',3,'webmaster','2007-06-27',NULL),
  (13343,140,'test',3,'webmaster','2007-06-27',NULL),
  (13344,140,'test',3,'webmaster','2007-06-27','2007-06-27'),
  (13345,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13346,140,'test',3,'webmaster','2007-06-27','2007-06-27'),
  (13347,1,'system',1,'SYSTEM','2007-06-27','2007-06-27'),
  (13360,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13361,1,'system',1,'SYSTEM','2007-06-27','2007-06-27'),
  (13380,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13400,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13420,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13440,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13441,1,'system',1,'SYSTEM','2007-06-27','2007-06-27'),
  (13442,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13460,1,'system',1,'SYSTEM','2007-06-27',NULL),
  (13480,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13481,1,'system',1,'SYSTEM','2007-06-28','2007-06-28'),
  (13482,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13483,1,'system',1,'SYSTEM','2007-06-28','2007-06-28'),
  (13484,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13485,1,'system',1,'SYSTEM','2007-06-28','2007-06-28'),
  (13486,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13487,1,'system',1,'SYSTEM','2007-06-28','2007-06-28'),
  (13488,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13489,1,'system',1,'SYSTEM','2007-06-28','2007-06-28'),
  (13490,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13491,1,'system',1,'SYSTEM','2007-06-28','2007-06-28'),
  (13492,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13500,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13520,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13540,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13541,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13542,1,'system',1,'SYSTEM','2007-06-28','2007-06-28'),
  (13543,1,'system',1,'SYSTEM','2007-06-28','2007-06-28'),
  (13560,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13561,1,'system',1,'SYSTEM','2007-06-28','2007-06-28'),
  (13562,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13563,1,'system',1,'SYSTEM','2007-06-28','2007-06-28'),
  (13564,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13565,1,'system',1,'SYSTEM','2007-06-28','2007-06-28'),
  (13566,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13567,1,'system',1,'SYSTEM','2007-06-28','2007-06-28'),
  (13568,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13580,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13600,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13620,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13640,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13660,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13680,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13700,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13720,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13740,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13760,1,'system',1,'SYSTEM','2007-06-28',NULL),
  (13780,1,'system',1,'SYSTEM','2007-06-29',NULL),
  (13800,1,'system',1,'SYSTEM','2007-06-29',NULL),
  (13801,1,'system',1,'SYSTEM','2007-06-29','2007-06-29'),
  (13802,1,'system',1,'SYSTEM','2007-06-29',NULL),
  (13820,1,'system',1,'SYSTEM','2007-06-29',NULL),
  (13821,1,'system',1,'SYSTEM','2007-06-29','2007-06-29'),
  (13840,1,'system',1,'SYSTEM','2007-06-29',NULL),
  (13841,1,'system',1,'SYSTEM','2007-06-29','2007-06-29'),
  (13842,1,'system',1,'SYSTEM','2007-06-29',NULL),
  (13860,1,'system',1,'SYSTEM','2007-06-29',NULL);

COMMIT;

#
# Data for the `tb_audit` table  (LIMIT 0,500)
#

INSERT INTO `tb_audit` (`audit_code`, `log_code`, `audit_date`, `audit_class`, `audit_entity`, `audit_action`, `audit_module`, `audit_method`, `audit_uri`, `audit_url`, `audit_remote_address`, `audit_remote_host`, `audit_entity_name`) VALUES 
  (1300,12800,'2007-06-26 10:24:22','com.iportal.model.system.SysModule',116,'insert','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Aeropuertos'),
  (1301,12802,'2007-06-26 10:34:46','com.iportal.model.system.SysModule',116,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Aeropuertos'),
  (1320,12822,'2007-06-26 12:06:27','com.iportal.model.system.SysUser',140,'insert','com.iportal.ctrl.system.SysUserAction','POST','/icaro/system/admin/sysUser/save.do','http://localhost:8080/icaro/system/admin/sysUser/save.do','127.0.0.1','127.0.0.1','test'),
  (1321,12824,'2007-06-26 12:06:50','com.iportal.model.system.SysUser',140,'update','com.iportal.ctrl.system.SysUserAction','POST','/icaro/system/update/sysUser/save.do','http://localhost:8080/icaro/system/update/sysUser/save.do','127.0.0.1','127.0.0.1','test'),
  (1340,12840,'2007-06-26 12:28:42','com.iportal.model.system.SysModule',116,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Aeropuertos'),
  (1360,12920,'2007-06-26 13:07:55','com.iportal.model.system.SysModule',117,'insert','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Opciones de Pasajeros'),
  (1361,12920,'2007-06-26 13:08:08','com.iportal.model.system.SysModule',117,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Opciones de Pasajeros'),
  (1362,12920,'2007-06-26 13:09:21','com.iportal.model.system.SysModule',118,'insert','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Clasificacion de Noticias'),
  (1363,12920,'2007-06-26 13:09:34','com.iportal.model.system.SysModule',117,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Opciones de Pasajeros'),
  (1364,12920,'2007-06-26 13:09:41','com.iportal.model.system.SysModule',118,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Clasificacion de Noticias'),
  (1365,12920,'2007-06-26 13:11:08','com.iportal.model.system.SysModule',119,'insert','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Clasificacion de Establecimientos'),
  (1366,12920,'2007-06-26 13:11:22','com.iportal.model.system.SysModule',118,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Clasificacion de Noticias'),
  (1380,12980,'2007-06-26 14:09:20','com.iportal.model.icaro.Airport',4,'insert','com.iportal.ctrl.system.entity.AirportAction','POST','/icaro/system/information/airport/save.do','http://localhost:8080/icaro/system/information/airport/save.do','127.0.0.1','127.0.0.1','Mariscal Sucre'),
  (1400,13000,'2007-06-26 14:23:34','com.iportal.model.icaro.Airport',4,'update','com.iportal.ctrl.system.entity.AirportAction','POST','/icaro/system/information/airport/save.do','http://localhost:8080/icaro/system/information/airport/save.do','127.0.0.1','127.0.0.1','Mariscal Sucre'),
  (1401,13000,'2007-06-26 14:23:43','com.iportal.model.icaro.Airport',4,'update','com.iportal.ctrl.system.entity.AirportAction','POST','/icaro/system/information/airport/save.do','http://localhost:8080/icaro/system/information/airport/save.do','127.0.0.1','127.0.0.1','Mariscal Sucre'),
  (1402,13000,'2007-06-26 14:24:14','com.iportal.model.icaro.Airport',4,'delete','com.iportal.ctrl.system.entity.AirportAction','POST','/icaro/system/information/airport.do','http://localhost:8080/icaro/system/information/airport.do','127.0.0.1','127.0.0.1','Mariscal Sucre'),
  (1403,13000,'2007-06-26 14:25:53','com.iportal.model.icaro.Airport',5,'insert','com.iportal.ctrl.system.entity.AirportAction','POST','/icaro/system/information/airport/save.do','http://localhost:8080/icaro/system/information/airport/save.do','127.0.0.1','127.0.0.1','Mariscal Sucre'),
  (1404,13000,'2007-06-26 14:26:01','com.iportal.model.icaro.Airport',5,'delete','com.iportal.ctrl.system.entity.AirportAction','POST','/icaro/system/information/airport.do','http://localhost:8080/icaro/system/information/airport.do','127.0.0.1','127.0.0.1','Mariscal Sucre'),
  (1405,13000,'2007-06-26 14:28:50','com.iportal.model.icaro.Airport',6,'insert','com.iportal.ctrl.system.entity.AirportAction','POST','/icaro/system/information/airport/save.do','http://localhost:8080/icaro/system/information/airport/save.do','127.0.0.1','127.0.0.1','map'),
  (1406,13000,'2007-06-26 14:28:55','com.iportal.model.icaro.Airport',6,'delete','com.iportal.ctrl.system.entity.AirportAction','POST','/icaro/system/information/airport.do','http://localhost:8080/icaro/system/information/airport.do','127.0.0.1','127.0.0.1','map'),
  (1407,13000,'2007-06-26 14:30:07','com.iportal.model.Catalogue',60,'insert','com.iportal.ctrl.system.entity.CatalogueAction','POST','/icaro/system/information/catalogue/save.do','http://localhost:8080/icaro/system/information/catalogue/save.do','127.0.0.1','127.0.0.1','1'),
  (1408,13000,'2007-06-26 14:30:11','com.iportal.model.Catalogue',60,'delete','com.iportal.ctrl.system.entity.CatalogueAction','POST','/icaro/system/information/catalogue.do','http://localhost:8080/icaro/system/information/catalogue.do','127.0.0.1','127.0.0.1','1'),
  (1409,13000,'2007-06-26 14:30:23','com.iportal.model.Catalogue',61,'insert','com.iportal.ctrl.system.entity.CatalogueAction','POST','/icaro/system/information/catalogue/save.do','http://localhost:8080/icaro/system/information/catalogue/save.do','127.0.0.1','127.0.0.1','qwe'),
  (1410,13000,'2007-06-26 14:31:01','com.iportal.model.Catalogue',61,'update','com.iportal.ctrl.system.entity.CatalogueAction','POST','/icaro/system/information/catalogue/save.do','http://localhost:8080/icaro/system/information/catalogue/save.do','127.0.0.1','127.0.0.1','qwe'),
  (1411,13000,'2007-06-26 14:31:05','com.iportal.model.Catalogue',61,'delete','com.iportal.ctrl.system.entity.CatalogueAction','POST','/icaro/system/information/catalogue.do','http://localhost:8080/icaro/system/information/catalogue.do','127.0.0.1','127.0.0.1','qwe'),
  (1412,13000,'2007-06-26 14:34:22','com.iportal.model.icaro.Airport',7,'insert','com.iportal.ctrl.system.entity.AirportAction','POST','/icaro/system/information/airport/save.do','http://localhost:8080/icaro/system/information/airport/save.do','127.0.0.1','127.0.0.1','1Intro'),
  (1413,13000,'2007-06-26 14:34:31','com.iportal.model.icaro.Airport',7,'delete','com.iportal.ctrl.system.entity.AirportAction','POST','/icaro/system/information/airport.do','http://localhost:8080/icaro/system/information/airport.do','127.0.0.1','127.0.0.1','1Intro'),
  (1420,13020,'2007-06-26 14:44:00','com.iportal.model.icaro.Airport',8,'insert','com.iportal.ctrl.system.entity.AirportAction','POST','/icaro/system/information/airport/save.do','http://localhost:8080/icaro/system/information/airport/save.do','127.0.0.1','127.0.0.1','map'),
  (1421,13020,'2007-06-26 14:44:05','com.iportal.model.icaro.Airport',8,'delete','com.iportal.ctrl.system.entity.AirportAction','POST','/icaro/system/information/airport.do','http://localhost:8080/icaro/system/information/airport.do','127.0.0.1','127.0.0.1','map'),
  (1422,13020,'2007-06-26 14:44:25','com.iportal.model.icaro.Airport',9,'insert','com.iportal.ctrl.system.entity.AirportAction','POST','/icaro/system/information/airport/save.do','http://localhost:8080/icaro/system/information/airport/save.do','127.0.0.1','127.0.0.1','Mariscal Sucre'),
  (1423,13020,'2007-06-26 15:05:01','com.iportal.model.system.SysModule',120,'insert','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Establecimientos'),
  (1424,13020,'2007-06-26 15:05:16','com.iportal.model.system.SysModule',120,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Establecimientos'),
  (1440,13042,'2007-06-26 15:36:39','com.iportal.model.system.SysModule',120,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Establecimientos'),
  (1460,13120,'2007-06-26 17:34:10','com.iportal.model.Catalogue',80,'insert','com.iportal.ctrl.system.entity.CatalogueAction','POST','/icaro/system/information/catalogue/save.do','http://localhost:8080/icaro/system/information/catalogue/save.do','127.0.0.1','127.0.0.1','Restaurante'),
  (1461,13120,'2007-06-26 17:34:28','com.iportal.model.Catalogue',81,'insert','com.iportal.ctrl.system.entity.CatalogueAction','POST','/icaro/system/information/catalogue/save.do','http://localhost:8080/icaro/system/information/catalogue/save.do','127.0.0.1','127.0.0.1','Hotel'),
  (1480,13160,'2007-06-27 09:37:07','com.iportal.model.icaro.office.Establishment',20,'insert','com.iportal.cart.ctrl.system.catalog.EstablishmentAction','POST','/icaro/system/catalogue/establishment/save.do','http://localhost:8080/icaro/system/catalogue/establishment/save.do','127.0.0.1','127.0.0.1','Cofradía'),
  (1500,13180,'2007-06-27 10:07:10','com.iportal.model.icaro.office.Establishment',40,'insert','com.iportal.cart.ctrl.system.catalog.EstablishmentAction','POST','/icaro/system/catalogue/establishment/save.do','http://localhost:8080/icaro/system/catalogue/establishment/save.do','127.0.0.1','127.0.0.1','KFC'),
  (1520,13200,'2007-06-27 10:49:43','com.iportal.model.icaro.office.Establishment',40,'update','com.iportal.cart.ctrl.system.catalog.EstablishmentAction','POST','/icaro/system/catalogue/establishment/save.do','http://localhost:8080/icaro/system/catalogue/establishment/save.do','127.0.0.1','127.0.0.1','KFC'),
  (1521,13200,'2007-06-27 11:03:54','com.iportal.model.icaro.Airport',10,'insert','com.iportal.ctrl.system.entity.AirportAction','POST','/icaro/system/information/airport/save.do','http://localhost:8080/icaro/system/information/airport/save.do','127.0.0.1','127.0.0.1','Simón Bolivar'),
  (1522,13200,'2007-06-27 11:15:29','com.iportal.model.icaro.office.Establishment',40,'update','com.iportal.cart.ctrl.system.catalog.EstablishmentAction','POST','/icaro/system/catalogue/establishment/save.do','http://localhost:8080/icaro/system/catalogue/establishment/save.do','127.0.0.1','127.0.0.1','KFC'),
  (1540,13260,'2007-06-27 11:37:34','com.iportal.model.system.SysUser',140,'update','com.iportal.ctrl.system.SysUserAction','POST','/icaro/system/update/sysUser/save.do','http://192.168.0.25:8080/icaro/system/update/sysUser/save.do','192.168.0.1','192.168.0.1','test'),
  (1541,13262,'2007-06-27 12:08:02','com.iportal.model.system.SysUser',140,'update','com.iportal.ctrl.system.SysUserAction','POST','/icaro/system/update/sysUser/save.do','http://192.168.0.25:8080/icaro/system/update/sysUser/save.do','192.168.0.1','192.168.0.1','test'),
  (1560,13240,'2007-06-27 11:38:04','com.iportal.model.icaro.office.Establishment',20,'update','com.iportal.cart.ctrl.system.catalog.EstablishmentAction','POST','/icaro/system/catalogue/establishment/save.do','http://localhost:8080/icaro/system/catalogue/establishment/save.do','127.0.0.1','127.0.0.1','Cofradía'),
  (1580,13280,'2007-06-27 11:43:42','com.iportal.model.icaro.office.Establishment',20,'update','com.iportal.cart.ctrl.system.catalog.EstablishmentAction','POST','/icaro/system/catalogue/establishment/save.do','http://localhost:8080/icaro/system/catalogue/establishment/save.do','127.0.0.1','127.0.0.1','Cofradía'),
  (1581,13280,'2007-06-27 11:44:22','com.iportal.model.icaro.office.Establishment',60,'insert','com.iportal.cart.ctrl.system.catalog.EstablishmentAction','POST','/icaro/system/catalogue/establishment/save.do','http://localhost:8080/icaro/system/catalogue/establishment/save.do','127.0.0.1','127.0.0.1','Establecimientos'),
  (1600,13300,'2007-06-27 11:47:24','com.iportal.model.icaro.office.Establishment',60,'update','com.iportal.cart.ctrl.system.catalog.EstablishmentAction','POST','/icaro/system/catalogue/establishment/save.do','http://localhost:8080/icaro/system/catalogue/establishment/save.do','127.0.0.1','127.0.0.1','Establecimientos'),
  (1601,13300,'2007-06-27 11:47:37','com.iportal.model.icaro.office.Establishment',40,'update','com.iportal.cart.ctrl.system.catalog.EstablishmentAction','POST','/icaro/system/catalogue/establishment/save.do','http://localhost:8080/icaro/system/catalogue/establishment/save.do','127.0.0.1','127.0.0.1','KFC'),
  (1602,13300,'2007-06-27 11:47:52','com.iportal.model.icaro.office.Establishment',60,'update','com.iportal.cart.ctrl.system.catalog.EstablishmentAction','POST','/icaro/system/catalogue/establishment/save.do','http://localhost:8080/icaro/system/catalogue/establishment/save.do','127.0.0.1','127.0.0.1','Establecimientos'),
  (1603,13300,'2007-06-27 12:35:42','com.iportal.cart.model.cart.PaymentType',2,'insert','com.iportal.cart.ctrl.system.cart.PaymentTypeAction','POST','/icaro/system/cart/paymentType/save.do','http://localhost:8080/icaro/system/cart/paymentType/save.do','127.0.0.1','127.0.0.1','Efectivo'),
  (1604,13300,'2007-06-27 12:35:52','com.iportal.cart.model.cart.PaymentType',2,'update','com.iportal.cart.ctrl.system.cart.PaymentTypeAction','POST','/icaro/system/cart/paymentType/save.do','http://localhost:8080/icaro/system/cart/paymentType/save.do','127.0.0.1','127.0.0.1','Efectivo'),
  (1605,13300,'2007-06-27 12:35:57','com.iportal.cart.model.cart.PaymentType',2,'delete','com.iportal.cart.ctrl.system.cart.PaymentTypeAction','POST','/icaro/system/cart/paymentType.do','http://localhost:8080/icaro/system/cart/paymentType.do','127.0.0.1','127.0.0.1','Efectivo'),
  (1606,13300,'2007-06-27 12:54:14','com.iportal.model.system.SysModule',121,'insert','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Directorio de Contactos'),
  (1607,13300,'2007-06-27 12:56:56','com.iportal.model.system.SysModule',121,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Directorio de Contactos'),
  (1620,13320,'2007-06-27 12:10:54','com.iportal.model.system.SysUser',140,'update','com.iportal.ctrl.system.SysUserAction','POST','/icaro/system/update/sysUser/save.do','http://localhost:8080/icaro/system/update/sysUser/save.do','127.0.0.1','127.0.0.1','test'),
  (1621,13320,'2007-06-27 12:11:36','com.iportal.model.system.SysUser',160,'insert','com.iportal.ctrl.system.SysUserAction','POST','/icaro/system/admin/sysUser/save.do','http://localhost:8080/icaro/system/admin/sysUser/save.do','127.0.0.1','127.0.0.1','otro'),
  (1622,13320,'2007-06-27 12:14:10','com.iportal.model.system.SysUser',160,'update','com.iportal.ctrl.system.SysUserAction','POST','/icaro/system/admin/sysUser/save.do','http://localhost:8080/icaro/system/admin/sysUser/save.do','127.0.0.1','127.0.0.1','otroa'),
  (1623,13320,'2007-06-27 12:14:15','com.iportal.model.system.SysUser',160,'delete','com.iportal.ctrl.system.SysUserAction','POST','/icaro/system/admin/sysUser.do','http://localhost:8080/icaro/system/admin/sysUser.do','127.0.0.1','127.0.0.1','otroa'),
  (1640,13340,'2007-06-27 12:39:17','com.iportal.model.system.SysUser',140,'update','com.iportal.ctrl.system.SysUserAction','POST','/icaro/system/admin/sysUser/save.do','http://192.168.0.25:8080/icaro/system/admin/sysUser/save.do','192.168.0.1','192.168.0.1','test'),
  (1641,13345,'2007-06-27 12:47:43','com.iportal.model.Country',1,'update','com.iportal.ctrl.system.portal.CountryAction','POST','/icaro/system/information/country/save.do','http://localhost:8080/icaro/system/information/country/save.do','127.0.0.1','127.0.0.1','Ecuadora'),
  (1642,13345,'2007-06-27 12:47:54','com.iportal.model.Country',80,'insert','com.iportal.ctrl.system.portal.CountryAction','POST','/icaro/system/information/country/save.do','http://localhost:8080/icaro/system/information/country/save.do','127.0.0.1','127.0.0.1','Colombia'),
  (1643,13345,'2007-06-27 12:48:03','com.iportal.model.Country',1,'update','com.iportal.ctrl.system.portal.CountryAction','POST','/icaro/system/information/country/save.do','http://localhost:8080/icaro/system/information/country/save.do','127.0.0.1','127.0.0.1','Ecuador'),
  (1644,13345,'2007-06-27 12:48:15','com.iportal.model.Country',81,'insert','com.iportal.ctrl.system.portal.CountryAction','POST','/icaro/system/information/country/save.do','http://localhost:8080/icaro/system/information/country/save.do','127.0.0.1','127.0.0.1','Peru'),
  (1645,13345,'2007-06-27 12:48:21','com.iportal.model.Country',81,'update','com.iportal.ctrl.system.portal.CountryAction','POST','/icaro/system/information/country/save.do','http://localhost:8080/icaro/system/information/country/save.do','127.0.0.1','127.0.0.1','Peru'),
  (1646,13345,'2007-06-27 12:48:32','com.iportal.model.Country',81,'update','com.iportal.ctrl.system.portal.CountryAction','POST','/icaro/system/information/country/save.do','http://localhost:8080/icaro/system/information/country/save.do','127.0.0.1','127.0.0.1','Perú'),
  (1647,13345,'2007-06-27 12:48:42','com.iportal.model.Country',81,'update','com.iportal.ctrl.system.portal.CountryAction','POST','/icaro/system/information/country/save.do','http://localhost:8080/icaro/system/information/country/save.do','127.0.0.1','127.0.0.1','Perú'),
  (1648,13345,'2007-06-27 12:48:48','com.iportal.model.Country',81,'delete','com.iportal.ctrl.system.portal.CountryAction','POST','/icaro/system/information/country.do','http://localhost:8080/icaro/system/information/country.do','127.0.0.1','127.0.0.1','Perú'),
  (1660,13440,'2007-06-27 14:13:07','com.iportal.model.icaro.office.Office',1,'insert','com.iportal.cart.ctrl.system.catalog.OfficeAction','POST','/icaro/system/catalogue/office/save.do','http://localhost:8080/icaro/system/catalogue/office/save.do','127.0.0.1','127.0.0.1','Norte'),
  (1661,13440,'2007-06-27 14:13:44','com.iportal.model.icaro.office.Office',1,'update','com.iportal.cart.ctrl.system.catalog.OfficeAction','POST','/icaro/system/catalogue/office/save.do','http://localhost:8080/icaro/system/catalogue/office/save.do','127.0.0.1','127.0.0.1','Norte'),
  (1662,13440,'2007-06-27 14:14:13','com.iportal.model.icaro.office.Office',2,'insert','com.iportal.cart.ctrl.system.catalog.OfficeAction','POST','/icaro/system/catalogue/office/save.do','http://localhost:8080/icaro/system/catalogue/office/save.do','127.0.0.1','127.0.0.1','Sur'),
  (1663,13440,'2007-06-27 14:15:24','com.iportal.model.icaro.office.Office',3,'insert','com.iportal.cart.ctrl.system.catalog.OfficeAction','POST','/icaro/system/catalogue/office/save.do','http://localhost:8080/icaro/system/catalogue/office/save.do','127.0.0.1','127.0.0.1','Centro'),
  (1664,13440,'2007-06-27 14:17:44','com.iportal.model.icaro.office.Office',4,'insert','com.iportal.cart.ctrl.system.catalog.OfficeAction','POST','/icaro/system/catalogue/office/save.do','http://localhost:8080/icaro/system/catalogue/office/save.do','127.0.0.1','127.0.0.1','Este'),
  (1665,13440,'2007-06-27 14:18:03','com.iportal.model.icaro.office.Office',4,'update','com.iportal.cart.ctrl.system.catalog.OfficeAction','POST','/icaro/system/catalogue/office/save.do','http://localhost:8080/icaro/system/catalogue/office/save.do','127.0.0.1','127.0.0.1','Este'),
  (1666,13440,'2007-06-27 14:18:08','com.iportal.model.icaro.office.Office',4,'delete','com.iportal.cart.ctrl.system.catalog.OfficeAction','POST','/icaro/system/catalogue/office.do','http://localhost:8080/icaro/system/catalogue/office.do','127.0.0.1','127.0.0.1','Este'),
  (1667,13440,'2007-06-27 14:32:39','com.iportal.model.system.SysModule',116,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Aeropuertos'),
  (1680,13480,'2007-06-28 09:31:16','com.iportal.model.system.SysModule',116,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Aeropuertos'),
  (1681,13482,'2007-06-28 09:36:11','com.iportal.model.system.SysModule',73,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Menu'),
  (1682,13482,'2007-06-28 09:36:21','com.iportal.model.system.SysModule',25,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Section'),
  (1683,13482,'2007-06-28 09:36:39','com.iportal.model.system.SysModule',114,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Access'),
  (1684,13484,'2007-06-28 09:39:27','com.iportal.model.portal.Content',30,'insert','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/section/save.do','http://localhost:8080/icaro/system/portal/section/save.do','127.0.0.1','127.0.0.1','Seccion 1'),
  (1685,13484,'2007-06-28 09:40:15','com.iportal.model.portal.Content',31,'insert','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/category/save.do','http://localhost:8080/icaro/system/portal/category/save.do','127.0.0.1','127.0.0.1','Categoria 1'),
  (1686,13484,'2007-06-28 09:52:06','com.iportal.model.system.SysModule',122,'insert','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Icaro'),
  (1687,13484,'2007-06-28 09:52:35','com.iportal.model.system.SysModule',93,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Audit'),
  (1688,13484,'2007-06-28 09:52:44','com.iportal.model.system.SysModule',122,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Icaro'),
  (1689,13486,'2007-06-28 09:53:36','com.iportal.model.system.SysModule',120,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Establecimientos'),
  (1690,13486,'2007-06-28 09:53:46','com.iportal.model.system.SysModule',121,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Directorio de Contactos'),
  (1691,13490,'2007-06-28 10:06:05','com.iportal.model.icaro.Airport',11,'insert','com.iportal.ctrl.system.entity.AirportAction','POST','/icaro/system/information/airport/save.do','http://localhost:8080/icaro/system/information/airport/save.do','127.0.0.1','127.0.0.1','Jose Joaquín de Olmedo'),
  (1692,13490,'2007-06-28 10:11:57','com.iportal.model.Catalogue',100,'insert','com.iportal.ctrl.system.entity.CatalogueAction','POST','/icaro/system/information/catalogue/save.do','http://localhost:8080/icaro/system/information/catalogue/save.do','127.0.0.1','127.0.0.1','Industria Aerea'),
  (1693,13490,'2007-06-28 10:12:15','com.iportal.model.Catalogue',101,'insert','com.iportal.ctrl.system.entity.CatalogueAction','POST','/icaro/system/information/catalogue/save.do','http://localhost:8080/icaro/system/information/catalogue/save.do','127.0.0.1','127.0.0.1','Icaro'),
  (1694,13490,'2007-06-28 10:15:14','com.iportal.model.Catalogue',100,'delete','com.iportal.ctrl.system.entity.CatalogueAction','POST','/icaro/system/information/catalogue.do','http://localhost:8080/icaro/system/information/catalogue.do','127.0.0.1','127.0.0.1','Industria Aerea'),
  (1695,13490,'2007-06-28 10:15:14','com.iportal.model.Catalogue',101,'delete','com.iportal.ctrl.system.entity.CatalogueAction','POST','/icaro/system/information/catalogue.do','http://localhost:8080/icaro/system/information/catalogue.do','127.0.0.1','127.0.0.1','Icaro'),
  (1697,13492,'2007-06-28 10:17:26','com.iportal.model.Catalogue',102,'insert','com.iportal.ctrl.system.entity.CatalogueAction','POST','/icaro/system/information/catalogue/save.do','http://localhost:8080/icaro/system/information/catalogue/save.do','127.0.0.1','127.0.0.1','Industria Aérea'),
  (1698,13492,'2007-06-28 10:17:32','com.iportal.model.Catalogue',103,'insert','com.iportal.ctrl.system.entity.CatalogueAction','POST','/icaro/system/information/catalogue/save.do','http://localhost:8080/icaro/system/information/catalogue/save.do','127.0.0.1','127.0.0.1','Icaro'),
  (1700,13540,'2007-06-28 11:07:44','com.iportal.model.City',43,'insert','com.iportal.ctrl.system.portal.CityAction','POST','/icaro/system/portal/city/save.do','http://localhost:8080/icaro/system/portal/city/save.do','127.0.0.1','127.0.0.1','otro'),
  (1701,13540,'2007-06-28 11:07:51','com.iportal.model.City',43,'delete','com.iportal.ctrl.system.portal.CityAction','POST','/icaro/system/portal/city.do','http://localhost:8080/icaro/system/portal/city.do','127.0.0.1','127.0.0.1','otro'),
  (1702,13540,'2007-06-28 11:14:37','com.iportal.model.portal.Content',30,'update','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/section/save.do','http://localhost:8080/icaro/system/portal/section/save.do','127.0.0.1','127.0.0.1','Seccion 1a'),
  (1703,13540,'2007-06-28 11:16:06','com.iportal.model.portal.Content',32,'insert','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/section/save.do','http://localhost:8080/icaro/system/portal/section/save.do','127.0.0.1','127.0.0.1','Seccion 2'),
  (1704,13540,'2007-06-28 11:16:18','com.iportal.model.portal.Content',32,'delete','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/section.do','http://localhost:8080/icaro/system/portal/section.do','127.0.0.1','127.0.0.1','Seccion 2'),
  (1705,13540,'2007-06-28 11:17:10','com.iportal.model.portal.Content',33,'insert','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/category/save.do','http://localhost:8080/icaro/system/portal/category/save.do','127.0.0.1','127.0.0.1','categoria2'),
  (1706,13540,'2007-06-28 11:17:15','com.iportal.model.portal.Content',33,'delete','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/category.do','http://localhost:8080/icaro/system/portal/category.do','127.0.0.1','127.0.0.1','categoria2'),
  (1707,13540,'2007-06-28 11:17:49','com.iportal.model.portal.Content',34,'insert','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/item/save.do','http://localhost:8080/icaro/system/portal/item/save.do','127.0.0.1','127.0.0.1','asas'),
  (1708,13540,'2007-06-28 11:17:58','com.iportal.model.portal.Content',34,'update','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/item/save.do','http://localhost:8080/icaro/system/portal/item/save.do','127.0.0.1','127.0.0.1','SLKJAKD'),
  (1709,13540,'2007-06-28 11:18:03','com.iportal.model.portal.Content',34,'update','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/item/save.do','http://localhost:8080/icaro/system/portal/item/save.do','127.0.0.1','127.0.0.1','SLKJAKD assd'),
  (1710,13540,'2007-06-28 11:18:15','com.iportal.model.portal.RelatedContent',200,'insert','com.iportal.ctrl.system.portal.RelatedContentAction','POST','/icaro/system/portal/content/related/save.do','http://localhost:8080/icaro/system/portal/content/related/save.do','127.0.0.1','127.0.0.1','sad'),
  (1711,13541,'2007-06-28 11:37:37','com.iportal.model.Catalogue',120,'insert','com.iportal.ctrl.system.entity.CatalogueAction','POST','/icaro/system/information/catalogue/save.do','http://localhost:8080/icaro/system/information/catalogue/save.do','127.0.0.1','127.0.0.1','Informacion Adicional'),
  (1712,13541,'2007-06-28 11:37:57','com.iportal.model.Catalogue',121,'insert','com.iportal.ctrl.system.entity.CatalogueAction','POST','/icaro/system/information/catalogue/save.do','http://localhost:8080/icaro/system/information/catalogue/save.do','127.0.0.1','127.0.0.1','Enlaces'),
  (1713,13541,'2007-06-28 11:57:05','com.iportal.model.portal.Content',34,'update','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/item/save.do','http://localhost:8080/icaro/system/portal/item/save.do','127.0.0.1','127.0.0.1','SLKJAKD assd'),
  (1714,13541,'2007-06-28 12:12:50','com.iportal.model.portal.Content',34,'update','com.iportal.ctrl.system.portal.ContentLinkAction','POST','/icaro/system/portal/content/link/save.do','http://localhost:8080/icaro/system/portal/content/link/save.do','127.0.0.1','127.0.0.1','SLKJAKD assd'),
  (1715,13541,'2007-06-28 12:13:16','com.iportal.model.portal.Content',34,'update','com.iportal.ctrl.system.portal.ContentLinkAction','POST','/icaro/system/portal/content/link/save.do','http://localhost:8080/icaro/system/portal/content/link/save.do','127.0.0.1','127.0.0.1','SLKJAKD assd'),
  (1716,13541,'2007-06-28 12:13:31','com.iportal.model.portal.Content',34,'update','com.iportal.ctrl.system.portal.ContentLinkAction','POST','/icaro/system/portal/content/link/save.do','http://localhost:8080/icaro/system/portal/content/link/save.do','127.0.0.1','127.0.0.1','SLKJAKD assd'),
  (1717,13541,'2007-06-28 12:13:39','com.iportal.model.portal.Content',34,'update','com.iportal.ctrl.system.portal.ContentLinkAction','POST','/icaro/system/portal/content/link.do','http://localhost:8080/icaro/system/portal/content/link.do','127.0.0.1','127.0.0.1','SLKJAKD assd'),
  (1718,13541,'2007-06-28 12:23:09','com.iportal.model.portal.Content',34,'update','com.iportal.ctrl.system.portal.ContentDocumentAction','POST','/icaro/system/portal/content/document/save.do','http://localhost:8080/icaro/system/portal/content/document/save.do','127.0.0.1','127.0.0.1','SLKJAKD assd'),
  (1719,13541,'2007-06-28 12:23:28','com.iportal.model.portal.Content',34,'update','com.iportal.ctrl.system.portal.ContentDocumentAction','POST','/icaro/system/portal/content/document/save.do','http://localhost:8080/icaro/system/portal/content/document/save.do','127.0.0.1','127.0.0.1','SLKJAKD assd'),
  (1720,13566,'2007-06-28 11:58:20','com.iportal.model.portal.Content',30,'update','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/section/save.do','http://localhost:8080/icaro/system/portal/section/save.do','127.0.0.1','127.0.0.1','Icaro'),
  (1721,13566,'2007-06-28 11:59:09','com.iportal.model.portal.Content',35,'insert','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/section/save.do','http://localhost:8080/icaro/system/portal/section/save.do','127.0.0.1','127.0.0.1','Compras en línea'),
  (1722,13566,'2007-06-28 11:59:47','com.iportal.model.portal.Content',36,'insert','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/section/save.do','http://localhost:8080/icaro/system/portal/section/save.do','127.0.0.1','127.0.0.1','Servicios'),
  (1723,13566,'2007-06-28 12:00:22','com.iportal.model.portal.Content',37,'insert','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/section/save.do','http://localhost:8080/icaro/system/portal/section/save.do','127.0.0.1','127.0.0.1','Socios Icaro'),
  (1724,13566,'2007-06-28 12:01:33','com.iportal.model.portal.MenuLanguage',1,'update','com.iportal.ctrl.system.portal.MenuAction','POST','/icaro/system/portal/menu/save.do','http://localhost:8080/icaro/system/portal/menu/save.do','127.0.0.1','127.0.0.1','Menú Principal (izquierda)'),
  (1740,13580,'2007-06-28 12:08:22','com.iportal.model.Access',240,'insert','com.iportal.ctrl.system.portal.AccessAction','POST','/icaro/system/portal/access/save.do','http://localhost:8080/icaro/system/portal/access/save.do','127.0.0.1','127.0.0.1','Icaro'),
  (1741,13580,'2007-06-28 12:08:54','com.iportal.model.portal.AccessMenu',260,'insert','com.iportal.ctrl.system.portal.AccessMenuAction','POST','/icaro/system/portal/menu/access/save.do','http://localhost:8080/icaro/system/portal/menu/access/save.do','127.0.0.1','127.0.0.1','Icaro'),
  (1760,13541,'2007-06-28 12:24:08','com.iportal.model.portal.Content',34,'update','com.iportal.ctrl.system.portal.ContentDocumentAction','POST','/icaro/system/portal/content/document/save.do','http://localhost:8080/icaro/system/portal/content/document/save.do','127.0.0.1','127.0.0.1','SLKJAKD assd'),
  (1761,13541,'2007-06-28 12:24:35','com.iportal.model.portal.Content',34,'update','com.iportal.ctrl.system.portal.ContentDocumentAction','POST','/icaro/system/portal/content/document.do','http://localhost:8080/icaro/system/portal/content/document.do','127.0.0.1','127.0.0.1','SLKJAKD assd'),
  (1763,13541,'2007-06-28 12:25:05','com.iportal.model.portal.RelatedContent',200,'delete','com.iportal.ctrl.system.portal.RelatedContentAction','POST','/icaro/system/portal/content/related.do','http://localhost:8080/icaro/system/portal/content/related.do','127.0.0.1','127.0.0.1','sad'),
  (1764,13541,'2007-06-28 12:25:13','com.iportal.model.portal.Content',34,'delete','com.iportal.ctrl.system.portal.ContentAction','POST','/icaro/system/portal/item.do','http://localhost:8080/icaro/system/portal/item.do','127.0.0.1','127.0.0.1','SLKJAKD assd'),
  (1780,13620,'2007-06-28 13:12:32','com.iportal.model.portal.AccessMenu',280,'insert','com.iportal.ctrl.system.portal.AccessMenuAction','POST','/icaro/system/portal/menu/access/save.do','http://localhost:8080/icaro/system/portal/menu/access/save.do','127.0.0.1','127.0.0.1','Icaro'),
  (1781,13620,'2007-06-28 13:14:12','com.iportal.model.Access',260,'insert','com.iportal.ctrl.system.portal.AccessAction','POST','/icaro/system/portal/access/save.do','http://localhost:8080/icaro/system/portal/access/save.do','127.0.0.1','127.0.0.1','Compras en Línea'),
  (1782,13620,'2007-06-28 13:14:48','com.iportal.model.Access',261,'insert','com.iportal.ctrl.system.portal.AccessAction','POST','/icaro/system/portal/access/save.do','http://localhost:8080/icaro/system/portal/access/save.do','127.0.0.1','127.0.0.1','Servicios'),
  (1783,13620,'2007-06-28 13:15:10','com.iportal.model.Access',262,'insert','com.iportal.ctrl.system.portal.AccessAction','POST','/icaro/system/portal/access/save.do','http://localhost:8080/icaro/system/portal/access/save.do','127.0.0.1','127.0.0.1','Socios Icaro'),
  (1784,13620,'2007-06-28 13:25:43','com.iportal.model.portal.AccessMenu',280,'update','com.iportal.ctrl.system.portal.AccessMenuAction','POST','/icaro/system/portal/menu/access/save.do','http://localhost:8080/icaro/system/portal/menu/access/save.do','127.0.0.1','127.0.0.1','Compras en Línea'),
  (1785,13620,'2007-06-28 13:25:54','com.iportal.model.portal.AccessMenu',281,'insert','com.iportal.ctrl.system.portal.AccessMenuAction','POST','/icaro/system/portal/menu/access/save.do','http://localhost:8080/icaro/system/portal/menu/access/save.do','127.0.0.1','127.0.0.1','Servicios'),
  (1786,13620,'2007-06-28 13:26:12','com.iportal.model.portal.AccessMenu',282,'insert','com.iportal.ctrl.system.portal.AccessMenuAction','POST','/icaro/system/portal/menu/access/save.do','http://localhost:8080/icaro/system/portal/menu/access/save.do','127.0.0.1','127.0.0.1','Socios Icaro'),
  (1787,13620,'2007-06-28 13:42:10','com.iportal.model.Access',263,'insert','com.iportal.ctrl.system.portal.AccessAction','POST','/icaro/system/portal/access/save.do','http://localhost:8080/icaro/system/portal/access/save.do','127.0.0.1','127.0.0.1','Niño Esperanza'),
  (1788,13620,'2007-06-28 13:44:20','com.iportal.model.Access',264,'insert','com.iportal.ctrl.system.portal.AccessAction','POST','/icaro/system/portal/access/save.do','http://localhost:8080/icaro/system/portal/access/save.do','127.0.0.1','127.0.0.1','Aldeas'),
  (1789,13620,'2007-06-28 13:44:51','com.iportal.model.portal.AccessMenu',283,'insert','com.iportal.ctrl.system.portal.AccessMenuAction','POST','/icaro/system/portal/menu/access/save.do','http://localhost:8080/icaro/system/portal/menu/access/save.do','127.0.0.1','127.0.0.1','Niño Esperanza'),
  (1790,13620,'2007-06-28 13:45:09','com.iportal.model.portal.AccessMenu',284,'insert','com.iportal.ctrl.system.portal.AccessMenuAction','POST','/icaro/system/portal/menu/access/save.do','http://localhost:8080/icaro/system/portal/menu/access/save.do','127.0.0.1','127.0.0.1','Aldeas'),
  (1800,13780,'2007-06-29 09:34:54','com.iportal.model.portal.AccessMenu',300,'insert','com.iportal.ctrl.system.portal.AccessMenuAction','POST','/icaro/system/portal/menu/access/save.do','http://localhost:8080/icaro/system/portal/menu/access/save.do','127.0.0.1','127.0.0.1','Icaro'),
  (1801,13780,'2007-06-29 09:35:48','com.iportal.model.portal.AccessMenu',301,'insert','com.iportal.ctrl.system.portal.AccessMenuAction','POST','/icaro/system/portal/menu/access/save.do','http://localhost:8080/icaro/system/portal/menu/access/save.do','127.0.0.1','127.0.0.1','Servicios'),
  (1802,13780,'2007-06-29 09:36:06','com.iportal.model.portal.AccessMenu',302,'insert','com.iportal.ctrl.system.portal.AccessMenuAction','POST','/icaro/system/portal/menu/access/save.do','http://localhost:8080/icaro/system/portal/menu/access/save.do','127.0.0.1','127.0.0.1','Socios Icaro'),
  (1803,13780,'2007-06-29 10:07:21','com.iportal.model.Access',262,'update','com.iportal.ctrl.system.portal.AccessAction','POST','/icaro/system/portal/access/save.do','http://localhost:8080/icaro/system/portal/access/save.do','127.0.0.1','127.0.0.1','Socios Icaro'),
  (1804,13780,'2007-06-29 10:07:45','com.iportal.model.Access',261,'update','com.iportal.ctrl.system.portal.AccessAction','POST','/icaro/system/portal/access/save.do','http://localhost:8080/icaro/system/portal/access/save.do','127.0.0.1','127.0.0.1','Servicios'),
  (1805,13780,'2007-06-29 10:08:08','com.iportal.model.Access',240,'update','com.iportal.ctrl.system.portal.AccessAction','POST','/icaro/system/portal/access/save.do','http://localhost:8080/icaro/system/portal/access/save.do','127.0.0.1','127.0.0.1','Icaro'),
  (1820,13800,'2007-06-29 10:54:09','com.iportal.model.Image',780,'insert','com.yage.struts.action.UploadMediaAction','POST','/icaro/popup/uploadMediaFile.do','http://localhost:8080/icaro/popup/uploadMediaFile.do','127.0.0.1','127.0.0.1','noticia1'),
  (1821,13800,'2007-06-29 10:54:16','com.iportal.model.Image',780,'update','com.yage.struts.action.UploadMediaAction','POST','/icaro/popup/uploadMediaFile.do','http://localhost:8080/icaro/popup/uploadMediaFile.do','127.0.0.1','127.0.0.1','noticia1'),
  (1822,13800,'2007-06-29 10:54:41','com.iportal.model.Image',781,'insert','com.yage.struts.action.UploadMediaAction','POST','/icaro/popup/uploadMediaFile.do','http://localhost:8080/icaro/popup/uploadMediaFile.do','127.0.0.1','127.0.0.1','noticia1Principal'),
  (1823,13800,'2007-06-29 10:55:02','com.iportal.model.Image',781,'update','com.yage.struts.action.UploadMediaAction','POST','/icaro/popup/uploadMediaFile.do','http://localhost:8080/icaro/popup/uploadMediaFile.do','127.0.0.1','127.0.0.1','noticia1Principal'),
  (1824,13800,'2007-06-29 10:55:19','com.iportal.model.news.News',60,'insert','com.iportal.ctrl.system.news.NewsAction','POST','/icaro/system/news/news/save.do','http://localhost:8080/icaro/system/news/news/save.do','127.0.0.1','127.0.0.1','Noticia 1'),
  (1825,13800,'2007-06-29 10:59:35','com.iportal.model.Image',782,'insert','com.yage.struts.action.UploadMediaAction','POST','/icaro/popup/uploadMediaFile.do','http://localhost:8080/icaro/popup/uploadMediaFile.do','127.0.0.1','127.0.0.1','noticia2'),
  (1826,13800,'2007-06-29 10:59:38','com.iportal.model.Image',782,'update','com.yage.struts.action.UploadMediaAction','POST','/icaro/popup/uploadMediaFile.do','http://localhost:8080/icaro/popup/uploadMediaFile.do','127.0.0.1','127.0.0.1','noticia2'),
  (1827,13800,'2007-06-29 11:00:01','com.iportal.model.Image',783,'insert','com.yage.struts.action.UploadMediaAction','POST','/icaro/popup/uploadMediaFile.do','http://localhost:8080/icaro/popup/uploadMediaFile.do','127.0.0.1','127.0.0.1','noticia2Principal'),
  (1828,13800,'2007-06-29 11:00:11','com.iportal.model.news.News',61,'insert','com.iportal.ctrl.system.news.NewsAction','POST','/icaro/system/news/news/save.do','http://localhost:8080/icaro/system/news/news/save.do','127.0.0.1','127.0.0.1','Noticia2'),
  (1829,13800,'2007-06-29 11:01:01','com.iportal.model.Image',784,'insert','com.yage.struts.action.UploadMediaAction','POST','/icaro/popup/uploadMediaFile.do','http://localhost:8080/icaro/popup/uploadMediaFile.do','127.0.0.1','127.0.0.1','noticia3'),
  (1830,13800,'2007-06-29 11:01:03','com.iportal.model.Image',784,'update','com.yage.struts.action.UploadMediaAction','POST','/icaro/popup/uploadMediaFile.do','http://localhost:8080/icaro/popup/uploadMediaFile.do','127.0.0.1','127.0.0.1','noticia3'),
  (1831,13800,'2007-06-29 11:01:24','com.iportal.model.Image',785,'insert','com.yage.struts.action.UploadMediaAction','POST','/icaro/popup/uploadMediaFile.do','http://localhost:8080/icaro/popup/uploadMediaFile.do','127.0.0.1','127.0.0.1','noticia3Principal'),
  (1832,13800,'2007-06-29 11:01:30','com.iportal.model.news.News',62,'insert','com.iportal.ctrl.system.news.NewsAction','POST','/icaro/system/news/news/save.do','http://localhost:8080/icaro/system/news/news/save.do','127.0.0.1','127.0.0.1','Noticia3'),
  (1840,13820,'2007-06-29 11:17:34','com.iportal.model.system.SysModule',123,'insert','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Vuelos'),
  (1841,13820,'2007-06-29 11:17:49','com.iportal.model.system.SysModule',123,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Vuelos'),
  (1842,13820,'2007-06-29 11:17:59','com.iportal.model.system.SysModule',120,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Establecimientos'),
  (1843,13820,'2007-06-29 11:18:09','com.iportal.model.system.SysModule',121,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Directorio de Contactos'),
  (1844,13820,'2007-06-29 11:18:17','com.iportal.model.system.SysModule',121,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Directorio de Contactos'),
  (1845,13820,'2007-06-29 11:18:26','com.iportal.model.system.SysModule',120,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Establecimientos'),
  (1846,13820,'2007-06-29 11:19:32','com.iportal.model.system.SysModule',111,'update','com.iportal.ctrl.system.SysModuleAction','POST','/icaro/system/admin/sysModule/save.do','http://localhost:8080/icaro/system/admin/sysModule/save.do','127.0.0.1','127.0.0.1','Rutas');

COMMIT;

#
# Data for the `tb_topic` table  (LIMIT 0,500)
#

INSERT INTO `tb_topic` (`topic_code`, `topic_name`, `topic_description`, `topic_enabled`) VALUES 
  (1,'General','descripción',1);

COMMIT;

#
# Data for the `tb_catalogue_type` table  (LIMIT 0,500)
#

INSERT INTO `tb_catalogue_type` (`catalogue_type_code`, `catalogue_type_name`) VALUES 
  (1,'Trabajos'),
  (2,'Noticias'),
  (3,'Eventos'),
  (4,'Documentos'),
  (5,'Links'),
  (6,'FAQ Types'),
  (7,'Opciones Pasajero'),
  (8,'Establecimientos'),
  (9,'Opciones Noticias'),
  (10,'Opciones Estados');

COMMIT;

#
# Data for the `tb_catalogue` table  (LIMIT 0,500)
#

INSERT INTO `tb_catalogue` (`catalogue_code`, `catalogue_type_code`, `catalogue_enabled`, `catalogue_name`, `catalogue_icon`, `catalogue_description`, `catalogue_email`, `catalogue_administrator`) VALUES 
  (1,2,1,'Noticias Institucionales',NULL,'Noticias Institucionales',NULL,NULL),
  (2,2,1,'Sala de Prensa',NULL,'Sala de Prensa',NULL,NULL),
  (80,8,1,'Restaurante',NULL,'',NULL,NULL),
  (81,8,1,'Hotel',NULL,'',NULL,NULL),
  (102,2,1,'Industria Aérea',NULL,'',NULL,NULL),
  (103,2,1,'Icaro',NULL,'',NULL,NULL),
  (120,4,1,'Informacion Adicional',NULL,'',NULL,NULL),
  (121,5,1,'Enlaces',NULL,'',NULL,NULL);

COMMIT;

#
# Data for the `tb_image_category` table  (LIMIT 0,500)
#

INSERT INTO `tb_image_category` (`image_category_code`, `image_category_parent`, `image_category_name`) VALUES 
  (1,NULL,'General'),
  (2,NULL,'Contenidos'),
  (3,NULL,'Noticias'),
  (4,NULL,'Eventos');

COMMIT;

#
# Data for the `tb_image` table  (LIMIT 0,500)
#

INSERT INTO `tb_image` (`image_code`, `image_category_code`, `image_name`, `image_path`, `image_height`, `image_width`) VALUES 
  (780,3,'noticia1','/fotos/noticias/image-000000142.jpg',62,62),
  (781,3,'noticia1Principal','/fotos/noticias/image-000000143.jpg',62,62),
  (782,3,'noticia2','/fotos/noticias/image-000000144.jpg',62,62),
  (783,3,'noticia2Principal','/fotos/noticias/image-000000145.jpg',100,100),
  (784,3,'noticia3','/fotos/noticias/image-000000146.jpg',62,62),
  (785,3,'noticia3Principal','/fotos/noticias/image-000000147.jpg',100,100);

COMMIT;

#
# Data for the `tb_portal_module` table  (LIMIT 0,500)
#

INSERT INTO `tb_portal_module` (`portal_module_code`, `portal_module_name`, `portal_module_key`, `portal_module_forward`) VALUES 
  (1,'Content','portalModule.content','content'),
  (2,'Cart','portalModule.cart','cart'),
  (3,'office','portalModule.office','office'),
  (4,'flight','portalModule.flight','flight'),
  (5,'fidelity','portalModule.fidelity','fidelity'),
  (6,'establishment','portalModule.establishment','establishment'),
  (7,'news','portalModule.news','news'),
  (8,'Contact','portalModule.contact','contact'),
  (9,'Account','portalModule.account','account'),
  (10,'Site Map','portalModule.map','map'),
  (11,'SearchForms','portalModule.search','search');

COMMIT;

#
# Data for the `tb_layout` table  (LIMIT 0,500)
#

INSERT INTO `tb_layout` (`layout_code`, `portal_module_code`, `layout_forward`) VALUES 
  (1,1,'content_layout1'),
  (2,1,'content_promotion'),
  (3,2,'news_layout1'),
  (4,3,'news_layout1'),
  (5,5,'brand_layout1'),
  (6,1,'content_layout6'),
  (7,6,'establishment_layout'),
  (8,1,'content_layout3'),
  (9,9,'account_layout1'),
  (10,8,'contact_layout1'),
  (11,9,'account_layout2'),
  (12,10,'promotion_layout1'),
  (13,11,'searchForms_legalDocs'),
  (14,12,'map'),
  (15,11,'searchForms_faqs'),
  (16,1,'content_popup'),
  (19,11,'search_general'),
  (23,1,'content_layout4'),
  (24,1,'content_layout5'),
  (25,11,'search_flight');

COMMIT;

#
# Data for the `tb_content` table  (LIMIT 0,500)
#

INSERT INTO `tb_content` (`content_code`, `access_level_code`, `layout_code`, `image_code`, `content_parent`, `image_access_code`, `language_code`, `content_title`, `content_intro`, `content_text`, `content_level`, `content_group`, `content_order`, `content_background`, `content_enabled`, `content_keywords`, `content_description`, `content_show_open`, `content_type`, `content_link`, `content_main`, `content_menu_alias`, `content_docs_count`, `content_support_docs_count`, `content_faqs_count`, `content_links_count`) VALUES 
  (30,1,1,NULL,NULL,NULL,1,'Icaro','Sinopsis Icaro','<p>Texto Icaro</p>',1,1,1,'',1,'Icaro',NULL,1,0,'',1,'Seccion 1',NULL,NULL,NULL,NULL),
  (31,1,1,NULL,30,NULL,1,'Categoria 1','Categoria 1','Categoria 1',2,0,1,'',1,'Categoria 1',NULL,0,0,'',1,'Categoria 1',NULL,NULL,NULL,NULL),
  (35,1,1,NULL,NULL,NULL,1,'Compras en línea','Sinopsis Compras en l&iacute;nea','Texto Compras en l&iacute;nea',1,0,2,'',1,'Compras en línea',NULL,1,0,'',1,'Compras en línea',NULL,NULL,NULL,NULL),
  (36,1,1,NULL,NULL,NULL,1,'Servicios','Sinopsis Servicios','Texto Servicios',1,0,3,'',1,'Servicios',NULL,1,0,'',1,'Servicios',NULL,NULL,NULL,NULL),
  (37,1,1,NULL,NULL,NULL,1,'Socios Icaro','Sinopsis Socios Icaro','Texto Socios Icaro',1,0,4,'',1,'Socios Icaro',NULL,1,0,'',1,'Socios Icaro',NULL,NULL,NULL,NULL);

COMMIT;

#
# Data for the `tb_document_type` table  (LIMIT 0,500)
#

INSERT INTO `tb_document_type` (`document_type_code`, `document_type_name`) VALUES 
  (1,'Adjuntos'),
  (2,'Material de Soporte');

COMMIT;

#
# Data for the `tb_document_display_mode` table  (LIMIT 0,500)
#

INSERT INTO `tb_document_display_mode` (`doc_display_mode_code`, `doc_display_mode_name`) VALUES 
  (1,'Link');

COMMIT;

#
# Data for the `tb_doc_container` table  (LIMIT 0,500)
#

INSERT INTO `tb_doc_container` (`doc_container_code`, `doc_display_mode_code`, `catalogue_code`, `document_type_code`, `doc_container_title`, `doc_container_description`, `doc_container_keywords`, `doc_container_from`, `doc_container_to`, `doc_container_path`, `doc_container_enabled`, `doc_container_size`, `doc_container_extension`) VALUES 
  (45,1,120,1,'Test0o','Este es una pruaa','Pago',NULL,NULL,'/documentos/compartido/gen--000005.txt',1,0,'txt'),
  (46,1,120,1,'Otro','sdsadsa','sdfsdf',NULL,NULL,'/documentos/compartido/gen--000006.swf',1,2413583,'swf');

COMMIT;

#
# Data for the `tb_link_type` table  (LIMIT 0,500)
#

INSERT INTO `tb_link_type` (`link_type_code`, `link_type_name`) VALUES 
  (1,'General'),
  (2,'Aplicación');

COMMIT;

#
# Data for the `tb_link_container` table  (LIMIT 0,500)
#

INSERT INTO `tb_link_container` (`link_container_code`, `catalogue_code`, `link_type_code`, `link_container_title`, `link_container_url`, `link_container_description`, `link_container_from`, `link_container_to`, `link_container_enabled`, `link_container_index`) VALUES 
  (20,121,2,'OK google','http://www.google.com','sdasdsadasd',NULL,NULL,1,NULL),
  (21,121,1,'otro','http://www.gmail.com','sdfsdfsdf',NULL,NULL,1,NULL);

COMMIT;

#
# Data for the `tb_id_type` table  (LIMIT 0,500)
#

INSERT INTO `tb_id_type` (`id_type_code`, `id_type_name`) VALUES 
  (1,'Cedula'),
  (2,'Pasaporte'),
  (3,'Otro');

COMMIT;

#
# Data for the `tb_establishment` table  (LIMIT 0,500)
#

INSERT INTO `tb_establishment` (`establishment_code`, `catalogue_code`, `city_code`, `establishment_name`, `establishment_enabled`, `establishment_address`, `establishment_phone1`, `establishment_image`, `establishment_phone2`, `establishment_fax`, `establishment_email`, `establishment_schedule`, `establishment_description`, `establishment_contact_name`, `establishment_contact_position`, `establishment_last_contact`) VALUES 
  (20,81,32,'Cofradía',1,'Urb. ccc','2635025','','222222','222222','',NULL,'detalle','jaja','','2007-06-04'),
  (40,80,840,'KFC',1,'san rafael','111111','/images/uploaded/establishment/brand_image-000001.jpg','222222','333333','fertamayo20@hotmail.com',NULL,'detalle KFC','fernando','cargo mio','2007-06-13'),
  (60,81,32,'Establecimientos',1,'jajnajsajhn','456455','','','','',NULL,'weqeqw','fer','',NULL);

COMMIT;

#
# Data for the `tb_layout_language` table  (LIMIT 0,500)
#

INSERT INTO `tb_layout_language` (`layout_lang_code`, `language_code`, `layout_code`, `layout_lang_name`) VALUES 
  (1,1,1,'Content'),
  (2,1,2,'Listado Proveedores'),
  (3,1,3,'News'),
  (4,1,4,'News'),
  (5,1,5,'Directorio de Contactos'),
  (6,1,6,'Contenido Secundario'),
  (7,1,7,'Establecimientos'),
  (8,1,8,'Promotion Info'),
  (9,1,9,'Sub-Home'),
  (10,1,10,'form'),
  (11,1,11,'Account'),
  (12,1,12,'Promotion'),
  (13,1,13,'Buscador Base Legal'),
  (14,1,14,'Mapa'),
  (15,1,15,'Buscador FAQs'),
  (16,1,16,'PopUp'),
  (19,1,19,'Buscador General'),
  (23,1,23,'Flash'),
  (24,1,24,'Links - banners'),
  (25,1,25,'Buscador de Vuelos');

COMMIT;

#
# Data for the `tb_news` table  (LIMIT 0,500)
#

INSERT INTO `tb_news` (`news_code`, `catalogue_code`, `image_intro_code`, `image_main_code`, `news_title`, `news_leading_text`, `news_text`, `news_from`, `news_to`, `news_enabled`, `news_publish_home`, `news_local_info`, `news_creation`, `news_index`) VALUES 
  (60,103,780,781,'Noticia 1','Sinopsis Noticia 1','Detalle Noticia 1','2007-06-04 00:00:00','2007-06-15 00:00:00',1,1,1,'2007-06-12 00:00:00',1),
  (61,103,782,783,'Noticia2','Sinopsis Noticia2','Detalle Noticia2','2007-06-04 00:00:00','2007-06-23 00:00:00',1,1,1,'2007-06-06 00:00:00',2),
  (62,103,784,785,'Noticia3','Sinopsis Noticia3','Detalle Noticia3','2007-06-13 00:00:00','2007-06-30 00:00:00',1,1,1,'2007-06-12 00:00:00',3);

COMMIT;

#
# Data for the `tb_office_type` table  (LIMIT 0,500)
#

INSERT INTO `tb_office_type` (`office_type_code`, `office_type_name`) VALUES 
  (1,'Oficina'),
  (2,'Punto de Venta');

COMMIT;

#
# Data for the `tb_office` table  (LIMIT 0,500)
#

INSERT INTO `tb_office` (`office_code`, `city_code`, `office_type_code`, `office_name`, `office_enabled`, `office_address`, `office_image`, `office_phone`, `office_fax`, `office_email`, `office_schedule`, `office_description`) VALUES 
  (1,32,1,'Norte',1,'Norte dir','','2340973','',NULL,'18-22',NULL),
  (2,32,1,'Sur',1,'Sur dir','','2340976','255555',NULL,'19-20',NULL),
  (3,4,2,'Centro',1,'Centro dir','','456545','4545454',NULL,'8-12',NULL);

COMMIT;

#
# Data for the `tb_order_status` table  (LIMIT 0,500)
#

INSERT INTO `tb_order_status` (`order_status_code`, `order_status_name`, `order_status_notes`) VALUES 
  (1,'Pendiente de cobro',NULL),
  (2,'Lista para despacho',NULL),
  (3,'Parcialmente despachada',NULL),
  (4,'Despachada',NULL),
  (5,'Entregada',NULL),
  (6,'Anulada',NULL);

COMMIT;

#
# Data for the `tb_payment_type` table  (LIMIT 0,500)
#

INSERT INTO `tb_payment_type` (`payment_type_code`, `payment_type_name`, `payment_type_enabled`, `payment_type_restricted`, `payment_type_discount`, `payment_type_external_code`, `payment_type_logo`) VALUES 
  (1,'Tarjeta de crédito',1,0,NULL,'com.iportal.cart.biz','');

COMMIT;

#
# Data for the `tb_payment_status` table  (LIMIT 0,500)
#

INSERT INTO `tb_payment_status` (`payment_status_code`, `payment_status_name`, `payment_status_external_code`, `payment_status_message`) VALUES 
  (1,'PENDIENTE',NULL,NULL),
  (2,'EXITOSO',NULL,NULL),
  (3,'FALLIDO',NULL,NULL),
  (4,'EN PROCESO','','');

COMMIT;

#
# Data for the `tb_sys_role` table  (LIMIT 0,500)
#

INSERT INTO `tb_sys_role` (`sys_role_code`, `sys_role_parent`, `sys_role_level`, `sys_role_name`, `sys_role_description`) VALUES 
  (1,NULL,1,'SYSTEM','System Administrator'),
  (2,1,2,'Administrador','Application Administrator'),
  (3,1,2,'webmaster','Usuario administrativo de contenidos');

COMMIT;

#
# Data for the `tb_sys_access_mode` table  (LIMIT 0,500)
#

INSERT INTO `tb_sys_access_mode` (`sys_access_mode_code`, `sys_access_mode_name`, `sys_access_mode_enabled`) VALUES 
  (1,'YAGE',1),
  (2,'Cliente',1);

COMMIT;

#
# Data for the `tb_sys_user` table  (LIMIT 0,500)
#

INSERT INTO `tb_sys_user` (`sys_user_code`, `sys_role_code`, `sys_access_mode_code`, `seller_code`, `sys_user_name`, `sys_user_password`, `sys_user_enabled`, `sys_user_application`, `sys_user_email`, `sys_user_first_name`, `sys_user_last_name`, `sys_user_reset`, `sys_user_registration_date`) VALUES 
  (1,1,1,NULL,'system','54b53072540eeeb8f8e9343e71f28176',1,0,'info@yage.com.ec','SYSTEM','CMS',1,'2006-12-04'),
  (140,3,1,NULL,'test','098f6bcd4621d373cade4e832627b4f6',1,0,'hernan.leon@yage.com.ec','Hernan','Leon',1,'2007-06-27');

COMMIT;

#
# Data for the `tb_page_log` table  (LIMIT 0,500)
#

INSERT INTO `tb_page_log` (`page_log_code`, `page_log_date`, `page_log_session_id`, `page_log_url`, `page_log_remote_address`, `page_log_remote_host`, `page_log_resource_code`, `page_log_resource_description`, `page_log_resource_type`, `page_log_user_agent`, `page_log_referrer`, `page_log_language`) VALUES 
  (2152,'2007-06-28 13:07:31','D261927D3098BF7886145222F59088C4','http://localhost:8080/icaro/portal/main.do','127.0.0.1','127.0.0.1',30,'Icaro','com.iportal.model.portal.Content','Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)','http://localhost:8080/icaro/general/home.do','es-ec'),
  (2153,'2007-06-28 16:50:35','A68BDFFDB2701655BABAAF3F67BDA9E3','http://localhost:8080/icaro/portal/main.do','127.0.0.1','127.0.0.1',30,'Icaro','com.iportal.model.portal.Content','Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)','http://localhost:8080/icaro/general/home.do','es-ec'),
  (2154,'2007-06-29 14:03:22','6A1431A89BE7A9209BEE0F3078635E76','http://localhost:8080/icaro/portal/main.do','127.0.0.1','127.0.0.1',30,'Icaro','com.iportal.model.portal.Content','Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)','http://localhost:8080/icaro/general/home.do','es-ec'),
  (2155,'2007-06-29 14:03:24','6A1431A89BE7A9209BEE0F3078635E76','http://localhost:8080/icaro/portal/content/content.do','127.0.0.1','127.0.0.1',30,'Icaro','com.iportal.model.portal.Content','Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)','http://localhost:8080/icaro/general/home.do','es-ec'),
  (2156,'2007-06-29 14:04:50','6A1431A89BE7A9209BEE0F3078635E76','http://localhost:8080/icaro/portal/main.do','127.0.0.1','127.0.0.1',35,'Compras en línea','com.iportal.model.portal.Content','Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)','http://localhost:8080/icaro/general/home.do','es-ec');

COMMIT;

#
# Data for the `tb_tax_type` table  (LIMIT 0,500)
#

INSERT INTO `tb_tax_type` (`tax_type_code`, `tax_type_name`) VALUES 
  (1,'IVA'),
  (2,'ICE'),
  (3,'IVA E ICE'),
  (4,'no aplica');

COMMIT;

#
# Data for the `tb_sequence` table  (LIMIT 0,500)
#

INSERT INTO `tb_sequence` (`name`, `next_val`) VALUES 
  ('access',14),
  ('access_level',3),
  ('access_menu',16),
  ('access_url',5),
  ('account',1),
  ('address',1),
  ('address_type',3),
  ('airport',12),
  ('area',2),
  ('article',2),
  ('audit',93),
  ('banner_name',25),
  ('brand',4),
  ('bulletin',1),
  ('catalogue',7),
  ('catalogue_type',14),
  ('category',5),
  ('checkout_step',1),
  ('city',44),
  ('contact_form',5),
  ('content',38),
  ('country',5),
  ('customer',10),
  ('customer_address',16),
  ('delivery_agency',1),
  ('delivery_status',1),
  ('delivery_status_detail',1),
  ('department_container',1),
  ('display_mode',4),
  ('document_display_mode',2),
  ('document_name',1),
  ('document_type',3),
  ('doc_container',3),
  ('establishment',4),
  ('event',1),
  ('event_date',1),
  ('event_subscriber',1),
  ('faq_container',1),
  ('general_name',1),
  ('historic',1),
  ('id_type',1),
  ('image',40),
  ('image_category',4),
  ('image_gallery',4),
  ('image_name',148),
  ('language',3),
  ('layout',25),
  ('layout_language',25),
  ('legal_doc',1),
  ('licitation_container',1),
  ('link_container',2),
  ('link_type',1),
  ('log',694),
  ('menu',10),
  ('menu_item',11),
  ('menu_language',11),
  ('news',4),
  ('occupation',1),
  ('office',1),
  ('order',10),
  ('order_detail',10),
  ('order_status',1),
  ('order_status_detail',1),
  ('page_log',2157),
  ('payment_status',1),
  ('payment_type',3),
  ('portal_module',15),
  ('position',2),
  ('position_requested',1),
  ('preference',1),
  ('product',9),
  ('products_related',1),
  ('related_content',11),
  ('requester',1),
  ('requester_work_city',1),
  ('seller',2),
  ('seq_access',26),
  ('seq_brand_image',2),
  ('seq_brand_logo',2),
  ('seq_category',7),
  ('seq_document',7),
  ('seq_establishment_image',2),
  ('seq_payment_type_logo',2),
  ('seq_product',29),
  ('seq_seller_image',4),
  ('seq_seller_logo',4),
  ('state',25),
  ('statistic_container',1),
  ('subscriber',1),
  ('subscriber_topic',1),
  ('sys_access_mode',3),
  ('sys_module',124),
  ('sys_params',11),
  ('sys_profile',91),
  ('sys_role',5),
  ('sys_user',9),
  ('tax',6),
  ('tax_detail',8),
  ('tax_rate',10),
  ('tax_type',6),
  ('template',2),
  ('topic',1),
  ('user',1),
  ('zone',1);

COMMIT;

#
# Data for the `tb_sys_module` table  (LIMIT 0,500)
#

INSERT INTO `tb_sys_module` (`sys_module_code`, `sys_module_parent`, `sys_access_mode_code`, `sys_module_level`, `sys_module_group`, `sys_module_name`, `sys_module_key`, `sys_module_url`, `sys_module_order_index`, `sys_module_x`, `sys_module_y`, `sys_module_show_panel`) VALUES 
  (1,NULL,2,1,1,'Administration','administration',NULL,1,-100,19,NULL),
  (2,1,1,2,0,'Module','administration.module','/system/admin/sysModule/frame.do',1,0,0,0),
  (3,1,1,2,0,'Role','administration.role','/system/admin/sysRole.do?action=list',3,0,0,0),
  (4,1,2,2,0,'User','administration.user','/system/admin/sysUser.do?action=list',4,0,0,0),
  (5,NULL,2,1,1,'General Information','information',NULL,2,-100,19,NULL),
  (6,5,2,2,1,'Info Geo','system.geoInfo',NULL,-1,0,0,1),
  (7,6,2,3,0,'Country','information.country','/system/information/country.do?action=list',1,0,0,0),
  (8,6,1,3,0,'State','information.state','/system/portal/state.do?action=list',2,0,0,0),
  (9,6,1,3,0,'City','information.city','/system/portal/city.do?action=list',3,0,0,0),
  (10,5,1,2,1,'Catalogues','information.catalogues',NULL,-1,0,0,0),
  (11,10,2,3,0,'Document Type','information.catalogues.document','/system/information/catalogue.do?action=list&typeCode=4',4,0,0,0),
  (12,10,1,3,0,'Cargos','information.job','/system/information/catalogue.do?action=list&typeCode=1',1,0,0,0),
  (13,10,1,3,0,'News Types','information.catalogues.news','/system/information/catalogue.do?action=list&typeCode=2',2,0,0,0),
  (14,10,2,3,0,'Event Type','information.catalogues.event','/system/information/catalogue.do?action=list&typeCode=3',3,0,0,0),
  (15,10,2,3,0,'Link Type','information.catalogues.link','/system/information/catalogue.do?action=list&typeCode=5',5,0,0,0),
  (16,10,2,3,0,'FAQ Type','information.catalogues.faq','/system/information/catalogue.do?action=list&typeCode=6',6,0,0,0),
  (17,10,2,3,0,'Rama de estudios','information.study.branch','/system/information/catalogue.do?action=list&typeCode=12',12,0,0,0),
  (18,10,2,3,0,'Categoría de Imagenes','information.catalogues.imageCategory','/system/information/imageCategory.do?action=list',14,0,0,0),
  (19,10,2,3,0,'Aspiración salarial','information.salary.aspiration','/system/information/catalogue.do?action=list&typeCode=13',13,0,0,0),
  (20,10,2,3,0,'Nivel de instrucción','information.instruction.level','/system/information/catalogue.do?action=list&typeCode=11',11,0,0,0),
  (21,10,2,3,0,'Estado civil','information.marital.status','/system/information/catalogue.do?action=list&typeCode=10',10,0,0,0),
  (22,NULL,1,1,1,'Services','service',NULL,5,-100,19,0),
  (23,NULL,2,1,1,'Portal','portal',NULL,3,-100,19,NULL),
  (24,23,2,2,0,'Language','portal.language','/system/portal/language.do?action=list',-1,0,0,NULL),
  (25,23,2,2,0,'Section','portal.section','/system/portal/section.do?action=list&level=1',2,0,0,0),
  (26,22,2,2,0,'News','news.news','/system/news/news.do?action=list',-1,0,0,0),
  (33,22,2,2,0,'Event','event.event','/system/event/event.do?action=list',2,0,0,0),
  (37,22,2,2,1,'Bulletin','bulletin.bulletin',NULL,-1,0,0,NULL),
  (38,37,2,3,0,'Template','bulletin.template','/system/bulletin/template.do?action=list',-1,0,0,NULL),
  (39,37,2,3,0,'Topic','bulletin.topic','/system/bulletin/topic.do?action=list',-1,0,0,NULL),
  (40,37,2,3,0,'Bulletin','bulletin.bulletin','/system/bulletin/bulletin.do?action=list',-1,0,0,NULL),
  (42,37,2,3,0,'Subscriber','bulletin.subscriber','/system/bulletin/subscriber.do?action=list',-1,0,0,NULL),
  (44,22,2,2,1,'Bolsa Trabajo','services.jobs','/system/jobs/area.do?action=list',-1,0,0,0),
  (45,44,2,3,0,'Jobs','services.jobs','/system/jobs/area.do?action=list',-1,0,0,0),
  (54,5,2,2,1,'Containers','information.container',NULL,-1,0,0,0),
  (55,54,2,3,0,'Documentos','information.container.document','/system/container/document.do?action=list&docTypeCode=1',1,0,0,0),
  (57,54,2,3,0,'Links','information.container.link','/system/container/link.do?action=list',2,0,0,0),
  (61,54,1,3,0,'FAQ Container','information.container.faq','/system/container/faq.do?action=list',3,0,0,0),
  (63,54,2,3,0,'Support Material','information.container.support','/system/container/document.do?action=list&docTypeCode=2',4,0,0,0),
  (65,23,2,2,0,'Category','portal.category','/system/portal/category.do?action=list&level=2',3,0,0,0),
  (66,23,2,2,0,'Content Item','portal.item','/system/portal/item.do?action=list&level=3',4,0,0,0),
  (70,54,2,3,0,'Images','information.images','/system/container/image.do?action=list',6,0,0,0),
  (73,23,2,2,0,'Menu','portal.menu','/system/portal/menu.do?action=list',1,0,0,0),
  (80,54,2,3,0,'Formularios','information.forms','/system/container/species.do?action=list&speciesType=1',11,0,0,0),
  (81,1,1,2,0,'File Extensions','fileExtensions','/system/admin/loadFileExtensions.do',6,0,0,0),
  (82,54,2,3,0,'Solicitudes','information.requests','/system/container/species.do?action=list&speciesType=3',12,0,0,0),
  (87,1,2,2,0,'Asignación de Usuarios','administration.assign.users','/system/store/ldap.do?action=list',5,0,0,0),
  (92,37,2,3,0,'Send Bulletin','bulletin.sendBulletin','/system/bulletin/step1.do?action=create',-1,0,0,0),
  (93,NULL,2,1,1,'Audit','audit','',7,-100,19,0),
  (94,93,2,2,0,'Audit Administration','audit.adminAudit','/system/admin/sysAudit.do?action=list',1,0,0,0),
  (95,93,2,2,0,'Reports','audit.reports','/system/admin/reportsPageLog.do?action=list',2,0,0,0),
  (97,1,2,2,0,'Menus Memoria','administration.memoryMenu','/system/admin/applicationScope.do',7,0,0,1),
  (100,NULL,2,1,1,'Cart','cart','',5,-100,19,0),
  (101,100,2,2,0,'Brand','cart.brand','/system/catalogue/brand.do?action=list',4,0,0,0),
  (102,100,2,2,0,'Category','cart.category','/system/catalogue/category/frame.do',4,0,0,0),
  (103,100,2,2,0,'Product','cart.product','/system/catalogue/product.do?action=list',2,0,0,0),
  (104,100,2,2,0,'Seller','cart.seller','/system/catalogue/seller.do?action=list',7,0,0,0),
  (105,100,2,2,0,'Order','cart.order','/system/cart/order.do?action=list',1,0,0,0),
  (106,110,2,3,0,'Delivery Agency','cart.shipping.deliveryAgency','/system/portal/deliveryAgency.do?action=list',2,0,0,0),
  (107,100,2,2,0,'Customers','cart.customer','/system/cart/customer.do?action=list',3,0,0,0),
  (108,100,2,2,0,'Tax','cart.taxType','/system/cart/taxType.do?action=list',8,0,0,1),
  (109,1,1,2,0,'SysParams','administration.sysParam','/system/admin/sysParam.do?action=list',2,0,0,0),
  (110,100,2,2,1,'Envios','cart.shipping','',0,0,0,0),
  (111,123,2,3,0,'Rutas','icaro.flight.route','/system/portal/route.do?action=list',1,0,0,0),
  (112,22,2,2,1,'Reports','service.report','',7,0,0,0),
  (113,112,2,3,0,'Contacts','service.report.contact','/system/report/contact.do?action=list',1,0,0,0),
  (114,23,1,2,0,'Access','portal.access','/system/portal/access.do?action=list',6,0,0,1),
  (115,100,2,2,0,'Payment Type','cart.paymentType','/system/cart/paymentType.do?action=list&listItems=true',8,0,0,0),
  (116,6,1,3,0,'Aeropuertos','information.catalogues.airport','/system/information/airport.do?action=list',4,0,0,0),
  (117,10,1,3,0,'Opciones de Pasajeros','information.catalogues.passengerOption','/system/information/catalogue.do?action=list&typeCode=7',-1,0,0,0),
  (119,10,1,3,0,'Clasificacion de Establecimientos','information.catalogues.statesClasificator','/system/information/catalogue.do?action=list&typeCode=10',-1,0,0,0),
  (120,122,2,2,0,'Establecimientos','cart.establishment','/system/catalogue/establishment.do?action=list',2,0,0,0),
  (121,122,2,2,0,'Directorio de Contactos','cart.office','/system/catalogue/office.do?action=list',3,0,0,0),
  (122,NULL,2,1,1,'Icaro','icaro','',6,-100,19,0),
  (123,122,2,2,1,'Vuelos','icaro.flight','',1,0,0,0);

COMMIT;

#
# Data for the `tb_sys_params` table  (LIMIT 0,500)
#

INSERT INTO `tb_sys_params` (`sys_params_code`, `sys_params_name`, `sys_params_value`, `sys_params_type`) VALUES 
  (1,'ALIGNET_IDACQUIRER','12345678','java.lang.Integer'),
  (2,'ALIGNET_IDCOMMERCE','123456789','java.lang.Integer'),
  (3,'ALIGNET_ACTION_URL','https://servicios.alignet.com/VPOS/MM/transactionStart.do','java.lang.String'),
  (4,'ALIGNET_IDTERMINAL','123546879','java.lang.String'),
  (5,'ALIGNET_CURRENCY','840','java.lang.String'),
  (6,'SHIPPING_TAX_CODE','3','java.lang.Long'),
  (7,'PAGE_SIZE','5','java.lang.Integer'),
  (8,'PAGE_NUMBER','1','java.lang.Integer'),
  (9,'CONTENT_CATALOG','1','java.lang.Long'),
  (10,'CONTENT_SELLER','3','java.lang.Long'),
  (11,'CONTENT_BRAND','4','java.lang.Long'),
  (12,'CONTENT_CART','7','java.lang.Long'),
  (13,'CART_INITIAL_ORDER_STATUS','1','java.lang.Long'),
  (14,'CART_INITIAL_DELIVERY_STATUS','1','java.lang.Long'),
  (15,'CONTENT_SEARCH','28','java.lang.Long');

COMMIT;

#
# Data for the `tb_sys_profile` table  (LIMIT 0,500)
#

INSERT INTO `tb_sys_profile` (`sys_profile_code`, `sys_role_code`, `sys_module_code`) VALUES 
  (1411,2,1),
  (1412,2,109),
  (1413,2,81),
  (1414,2,4),
  (1415,2,97),
  (1416,2,100),
  (1417,2,105),
  (1418,2,103),
  (1419,2,108),
  (1420,2,107),
  (1421,2,115),
  (1422,2,102),
  (1423,2,110),
  (1424,2,111),
  (1425,2,106),
  (1487,3,5),
  (1488,3,6),
  (1489,3,8),
  (1490,3,9),
  (1491,3,7),
  (1492,3,23),
  (1493,3,25),
  (1494,3,100),
  (1495,3,103),
  (1760,1,1),
  (1761,1,97),
  (1762,1,109),
  (1763,1,3),
  (1764,1,81),
  (1765,1,4),
  (1766,1,2),
  (1767,1,5),
  (1768,1,10),
  (1769,1,117),
  (1770,1,11),
  (1771,1,15),
  (1772,1,119),
  (1773,1,13),
  (1774,1,6),
  (1775,1,9),
  (1776,1,7),
  (1777,1,8),
  (1778,1,116),
  (1779,1,23),
  (1780,1,25),
  (1781,1,73),
  (1782,1,65),
  (1783,1,114),
  (1784,1,66),
  (1785,1,22),
  (1786,1,26),
  (1787,1,112),
  (1788,1,113),
  (1789,1,100),
  (1790,1,103),
  (1791,1,115),
  (1792,1,108),
  (1793,1,107),
  (1794,1,102),
  (1795,1,110),
  (1796,1,106),
  (1797,1,105),
  (1798,1,122),
  (1799,1,123),
  (1800,1,111),
  (1801,1,120),
  (1802,1,121),
  (1803,1,93),
  (1804,1,95),
  (1805,1,94);

COMMIT;

#
# Data for the `tb_tax` table  (LIMIT 0,500)
#

INSERT INTO `tb_tax` (`tax_code`, `tax_name`) VALUES 
  (3,'IVA'),
  (4,'ICE');

COMMIT;

#
# Data for the `tb_tax_applied` table  (LIMIT 0,500)
#

INSERT INTO `tb_tax_applied` (`tax_type_code`, `tax_code`) VALUES 
  (1,3),
  (2,4),
  (3,3),
  (3,4);

COMMIT;

#
# Data for the `tb_tax_rate` table  (LIMIT 0,500)
#

INSERT INTO `tb_tax_rate` (`tax_rate_code`, `tax_code`, `tax_rate_value`, `tax_rate_since`, `tax_rate_until`) VALUES 
  (1,3,0.1200,'2000-01-01 00:00:00','3000-01-01 00:00:00'),
  (2,4,0.1500,'2000-01-01 00:00:00','3000-01-01 00:00:00');

COMMIT;

