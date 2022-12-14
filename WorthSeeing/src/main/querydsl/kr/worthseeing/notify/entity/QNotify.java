package kr.worthseeing.notify.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotify is a Querydsl query type for Notify
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotify extends EntityPathBase<Notify> {

    private static final long serialVersionUID = 2134287911L;

    public static final QNotify notify = new QNotify("notify");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> groupBlock_seq = createNumber("groupBlock_seq", Integer.class);

    public final NumberPath<Integer> notify_seq = createNumber("notify_seq", Integer.class);

    public final DateTimePath<java.util.Date> notifyTime = createDateTime("notifyTime", java.util.Date.class);

    public final StringPath title = createString("title");

    public QNotify(String variable) {
        super(Notify.class, forVariable(variable));
    }

    public QNotify(Path<? extends Notify> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotify(PathMetadata metadata) {
        super(Notify.class, metadata);
    }

}

