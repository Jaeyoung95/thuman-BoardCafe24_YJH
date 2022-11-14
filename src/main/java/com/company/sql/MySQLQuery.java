package com.company.sql;

public class MySQLQuery {
	
	// USERS SQL
	public static final String USERS_INSERT="insert into usersyjh(id,password,name) values(?,?,?)";
	// 회원 추가.
	public static final String USERS_CHECK_SELECT="select * from usersyjh where id=?";
	// 회원 가입여부 체크
	public static final String USERS_LOGIN_SELECT="select * from usersyjh where id=? and password=?";
	// 회원 ID PW 정확히 입력 후 로그인 체크
	
	// BOARD SQL
	public static final String BOARD_INSERT="insert into boardyjh(seq,title,nickname,content,userid) values((select ifnull(max(seq),0)+1 from boardyjh subquery),?,?,?,?)";
	public static final String REPLYBOARD2_INSERT="insert into replyboard_2yjh(boardseq,seq,rseq,nickname,regdate,content,userid) values(?,?,(select ifnull(max(rseq),0)+1 from replyboard_2yjh subquery),?,now(),?,?)";
	public static final String REPLYBOARD_INSERT="insert into replyboardyjh(boardseq,seq,nickname,content,userid) values(?,(select ifnull(max(seq),0)+1 from replyboardyjh subquery),?,?,?)";
	public static final String BOARD_DELETE="delete from boardyjh where seq=?";
	public static final String BOARDLIKE_DELETE="delete from boardlikeyjh where id=? and seq=?";
	public static final String REPLYBOARD2_DELETE="delete from replyboard_2yjh where boardseq=? and seq=? and rseq=?";
	public static final String REPLYBOARD2_CASCADE_DELETE="delete from replyboard_2yjh where boardseq=? and seq=?";
	public static final String REPLYBOARD_DELETE="delete from replyboardyjh where boardseq=? and seq=?";
	public static final String BOARD_CNT_UPDATE="update boardyjh set cnt=cnt+1 where seq=?";
	public static final String BOARD_SELECT="select seq,title,nickname,content,date_format(regdate,'%Y-%m-%d') as regdate,cnt,userid from boardyjh where seq=?";
	public static final String REPLYBOARD_SELECT="select boardseq,seq,nickname,date_format(regdate,'%Y-%m-%d %H:%i:%s') as regdate,content,userid from replyboardyjh where boardseq=? order by seq desc";
	public static final String REPLYBOARD2_SELECT="select boardseq,seq,rseq,nickname,date_format(regdate,'%Y-%m-%d %H:%i:%s') as regdate,content,userid from replyboard_2yjh order by boardseq desc,seq desc,rseq desc";
	public static final String BOARDLIKE_TOTAL_SELECT="select sum(good),sum(bad) from boardlikeyjh where seq=?";
	public static final String BOARDLIKE_CHECK_SELECT="select isCheckCode from boardlikeyjh where id=? and seq=?";
	
	public static final String BOARD_LIST_SELECT="select seq,title,nickname,content,date_format(regdate,'%Y-%m-%d') as regdate,cnt,userid from boardyjh order by seq desc limit ?,?";
	
	public static final String BOARD_COUNT_SELECT="select count(seq) from boardyjh";
	public static final String BOARD_SEARCH_COUNT_SELECT1="select count(*) from boardyjh where ";
	public static final String BOARD_SEARCH_COUNT_SELECT2=" like Concat('%',IFNULL(?, ''),'%')";
	public static final String BOARD_SEARCH_LIST1="select seq,title,nickname,content,date_format(regdate,'%Y-%m-%d') as regdate,cnt,userid from boardyjh where ";
	public static final String BOARD_SEARCH_LIST2=" like Concat('%' , IFNULL(?, '') , '%') order by seq desc limit ?,?";
	
	public static final String BOARD_UPDATE="update boardyjh set title=?,content=? where seq=?";
	public static final String BOARDLIKE_SELECT="select * from boardlikeyjh where id=? and seq=?";
	public static final String REPLYBOARD2_UPDATE="update replyboard_2yjh set content=?,regdate=now() where boardseq=? and seq=? and rseq=?";
	public static final String REPLYBOARD_UPDATE="update replyboardyjh set content=?,regdate=now() where boardseq=? and seq=?";
}
