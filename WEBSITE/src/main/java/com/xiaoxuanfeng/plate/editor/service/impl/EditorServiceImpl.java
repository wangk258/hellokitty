package com.xiaoxuanfeng.plate.editor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoxuanfeng.plate.editor.dao.EditorDao;
import com.xiaoxuanfeng.plate.editor.domain.Editor;
import com.xiaoxuanfeng.plate.editor.service.EditorService;

import common.base.BaseDao;
import common.base.BaseServiceImpl;
@Service
public class EditorServiceImpl extends BaseServiceImpl<Editor> implements EditorService {
	@Autowired
	private EditorDao editorDao;
	
	@Override
	public BaseDao<Editor> getDao() {
		return editorDao;
	}
}
