package kr.worthseeing.status.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStatus is a Querydsl query type for Status
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStatus extends EntityPathBase<Status> {

    private static final long serialVersionUID = 257571769L;

    public static final QStatus status = new QStatus("status");

    public final StringPath firstCode = createString("firstCode");

    public final StringPath secondCode = createString("secondCode");

    public final NumberPath<Integer> status_seq = createNumber("status_seq", Integer.class);

    public QStatus(String variable) {
        super(Status.class, forVariable(variable));
    }

    public QStatus(Path<? extends Status> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStatus(PathMetadata metadata) {
        super(Status.class, metadata);
    }

}

