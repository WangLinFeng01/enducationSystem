package com.whf.dao;

import java.util.List;

import com.whf.pojo.Paper;

public interface PaperDao {

	//�õ�����Ԫ�صı�
	List<Paper> gettableDatas();
	//ɸѡ�¼�
	List<Paper> getChoiceDatas(Integer obj);
}
