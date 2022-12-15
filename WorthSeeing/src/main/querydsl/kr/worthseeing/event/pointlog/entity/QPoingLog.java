package kr.worthseeing.event.pointlog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPoingLog is a Querydsl query type for PoingLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPoingLog extends EntityPathBase<PoingLog> {

    private static final long serialVersionUID = 1432868190L;

    public static final QPoingLog poingLog = new QPoingLog("poingLog");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final DateTimePath<java.util.Date> pointDate = createDateTime("pointDate", java.util.Date.class);

    public final NumberPath<Integer> pointLog_seq = createNumber("pointLog_seq", Integer.class);

    public final StringPath userid = createString("userid");

    public QPoingLog(String variable) {
        super(PoingLog.class, forVariable(variable));
    }

    public QPoingLog(Path<? extends PoingLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPoingLog(PathMetadata metadata) {
        super(PoingLog.class, metadata);
    }

}

