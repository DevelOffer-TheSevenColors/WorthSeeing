package kr.worthseeing.main.message.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMessage is a Querydsl query type for Message
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMessage extends EntityPathBase<Message> {

    private static final long serialVersionUID = -1238041024L;

    public static final QMessage message = new QMessage("message");

    public final NumberPath<Integer> message_seq = createNumber("message_seq", Integer.class);

    public final StringPath msg = createString("msg");

    public final DateTimePath<java.util.Date> sendTime = createDateTime("sendTime", java.util.Date.class);

    public final StringPath toUser = createString("toUser");

    public QMessage(String variable) {
        super(Message.class, forVariable(variable));
    }

    public QMessage(Path<? extends Message> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMessage(PathMetadata metadata) {
        super(Message.class, metadata);
    }

}

