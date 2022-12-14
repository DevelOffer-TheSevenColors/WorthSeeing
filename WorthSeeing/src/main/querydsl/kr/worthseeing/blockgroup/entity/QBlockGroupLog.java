package kr.worthseeing.blockgroup.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlockGroupLog is a Querydsl query type for BlockGroupLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlockGroupLog extends EntityPathBase<BlockGroupLog> {

    private static final long serialVersionUID = 267145259L;

    public static final QBlockGroupLog blockGroupLog = new QBlockGroupLog("blockGroupLog");

    public final NumberPath<Integer> avgPrice = createNumber("avgPrice", Integer.class);

    public final StringPath cImg = createString("cImg");

    public final NumberPath<Integer> clickCnt = createNumber("clickCnt", Integer.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Integer> groupBlock_seq = createNumber("groupBlock_seq", Integer.class);

    public final NumberPath<Integer> groupBlockLog_seq = createNumber("groupBlockLog_seq", Integer.class);

    public final DateTimePath<java.util.Date> groupDate = createDateTime("groupDate", java.util.Date.class);

    public final StringPath linkUrl = createString("linkUrl");

    public final StringPath sImg = createString("sImg");

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final StringPath userId = createString("userId");

    public QBlockGroupLog(String variable) {
        super(BlockGroupLog.class, forVariable(variable));
    }

    public QBlockGroupLog(Path<? extends BlockGroupLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlockGroupLog(PathMetadata metadata) {
        super(BlockGroupLog.class, metadata);
    }

}

