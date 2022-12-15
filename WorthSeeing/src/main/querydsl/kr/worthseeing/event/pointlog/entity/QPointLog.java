package kr.worthseeing.event.pointlog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointLog is a Querydsl query type for PointLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointLog extends EntityPathBase<PointLog> {

    private static final long serialVersionUID = 1433255473L;

    public static final QPointLog pointLog = new QPointLog("pointLog");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final DateTimePath<java.util.Date> pointDate = createDateTime("pointDate", java.util.Date.class);

    public final NumberPath<Integer> pointLog_seq = createNumber("pointLog_seq", Integer.class);

    public final StringPath userid = createString("userid");

    public QPointLog(String variable) {
        super(PointLog.class, forVariable(variable));
    }

    public QPointLog(Path<? extends PointLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointLog(PathMetadata metadata) {
        super(PointLog.class, metadata);
    }

}

