package kr.worthseeing.notify.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNotify is a Querydsl query type for Notify
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotify extends EntityPathBase<Notify> {

    private static final long serialVersionUID = 2134287911L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotify notify = new QNotify("notify");

    public final kr.worthseeing.blockgroup.entity.QBlockGroup blockGroup;

    public final StringPath content = createString("content");

    public final NumberPath<Integer> notify_seq = createNumber("notify_seq", Integer.class);

    public final DateTimePath<java.util.Date> notifyTime = createDateTime("notifyTime", java.util.Date.class);

    public final kr.worthseeing.status.entity.QStatus status;

    public final StringPath title = createString("title");

    public final kr.worthseeing.users.entity.QUsers users;

    public QNotify(String variable) {
        this(Notify.class, forVariable(variable), INITS);
    }

    public QNotify(Path<? extends Notify> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNotify(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNotify(PathMetadata metadata, PathInits inits) {
        this(Notify.class, metadata, inits);
    }

    public QNotify(Class<? extends Notify> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blockGroup = inits.isInitialized("blockGroup") ? new kr.worthseeing.blockgroup.entity.QBlockGroup(forProperty("blockGroup"), inits.get("blockGroup")) : null;
        this.status = inits.isInitialized("status") ? new kr.worthseeing.status.entity.QStatus(forProperty("status")) : null;
        this.users = inits.isInitialized("users") ? new kr.worthseeing.users.entity.QUsers(forProperty("users"), inits.get("users")) : null;
    }

}

