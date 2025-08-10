package com.react.ming.test.src.mapper;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;

public class CursorHandler<T> implements AutoCloseable{

    public SqlSession session;
    public Cursor<T> cursor;

    public CursorHandler(SqlSession session, Cursor<T> cursor) {
        this.session = session;
        this.cursor = cursor;
    }

    @Override
    public void close() throws Exception {
        try { if (cursor != null) cursor.close(); } finally { if (session != null) session.close(); }
    }
}
