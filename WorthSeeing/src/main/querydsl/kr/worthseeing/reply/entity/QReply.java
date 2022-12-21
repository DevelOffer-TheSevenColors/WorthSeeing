package kr.worthseeing.reply.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReply is a Querydsl query type for Reply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReply extends EntityPathBase<Reply> {

    private static final long serialVersionUID = 2116724667L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReply reply = new QReply("reply");

    public final kr.worthseeing.notify.entity.QNotify notify;

    public final StringPath replyContent = createString("replyContent");

    public final DateTimePath<java.util.Date> replyDate = createDateTime("replyDate", java.util.Date.class);

    public final StringPath replyer = createString("replyer");

    public final NumberPath<Integer> replySeq = createNumber("replySeq", Integer.class);

    public QReply(String variable) {
        this(Reply.class, forVariable(variable), INITS);
    }

    public QReply(Path<? extends Reply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReply(PathMetadata metadata, PathInits inits) {
        this(Reply.class, metadata, inits);
    }

    public QReply(Class<? extends Reply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.notify = inits.isInitialized("notify") ? new kr.worthseeing.notify.entity.QNotify(forProperty("notify"), inits.get("notify")) : null;
    }

}

