package com.dao;

import java.util.List;

import com.pojo.Paper;

public interface PaperDao {

	//�õ�����Ԫ�صı�
	List<Paper> gettableDatas();
	//ɸѡ�¼�
	List<Paper> getChoiceDatas(Integer obj);
	//����Ծ�
	public Integer addPaper(Paper paper);
	//��ѯ�Ծ�id
	public List<String> queryPaperId();
}
