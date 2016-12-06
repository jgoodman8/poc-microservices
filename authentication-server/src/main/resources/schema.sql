
DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users(
  id INTEGER NOT NULL PRIMARY KEY,
  username varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  enabled boolean NOT NULL DEFAULT TRUE,
  authority varchar(50) NOT NULL
);

DROP TABLE IF EXISTS ClientDetails CASCADE;
CREATE TABLE ClientDetails(
  appId varchar(256) NOT NULL PRIMARY KEY,
  resourceIds varchar(256) DEFAULT NULL,
  appSecret varchar(256) DEFAULT NULL,
  scope varchar(256) DEFAULT NULL,
  grantTypes varchar(256) DEFAULT NULL,
  redirectUrl varchar(256) DEFAULT NULL,
  authorities varchar(256) DEFAULT NULL,
  access_token_validity INTEGER DEFAULT NULL,
  refresh_token_validity INTEGER DEFAULT NULL,
  additionalInformation varchar(4096) DEFAULT NULL
);


DROP TABLE IF EXISTS authorities;
CREATE TABLE authorities(
  id INTEGER NOT NULL,
  /*username varchar(50) NOT NULL,*/
  authority varchar(50) NOT NULL,
  CONSTRAINT AuthorityFK FOREIGN KEY (id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT AuthoritiesPK PRIMARY KEY (id, authority)  
);


DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token(
  token_id varchar(256) DEFAULT NULL,
  token bytea,
  authentication_id varchar(256) DEFAULT NULL,
  user_name varchar(256) DEFAULT NULL,
  client_id varchar(256) DEFAULT NULL,
  authentication bytea,
  refresh_token varchar(256) DEFAULT NULL
);


DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details(
  client_id varchar(256) NOT NULL PRIMARY KEY,
  resource_ids varchar(256) DEFAULT NULL,
  client_secret varchar(256) DEFAULT NULL,
  scope varchar(256) DEFAULT NULL,
  authorized_grant_types varchar(256) DEFAULT NULL,
  web_server_redirect_uri varchar(256) DEFAULT NULL,
  authorities varchar(256) DEFAULT NULL,
  access_token_validity INTEGER DEFAULT NULL,
  refresh_token_validity INTEGER DEFAULT NULL,
  additional_information varchar(4096) DEFAULT NULL,
  autoapprove varchar(256) DEFAULT NULL
);


DROP TABLE IF EXISTS oauth_code;
CREATE TABLE oauth_code(
  code varchar(256) DEFAULT NULL,
  authentication bytea
);


DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token(
  token_id varchar(256) DEFAULT NULL,
  token bytea,
  authentication bytea
);


