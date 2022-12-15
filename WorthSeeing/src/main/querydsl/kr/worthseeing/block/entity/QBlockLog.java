package kr.worthseeing.block.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlockLog is a Querydsl query type for BlockLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlockLog extends EntityPathBase<BlockLog> {

    private static final long serialVersionUID = -2086864727L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlockLog blockLog = new QBlockLog("blockLog");

    public final NumberPath<Integer> block_seq = createNumber("block_seq", Integer.class);

    public final kr.worthseeing.blockgroup.entity.QBlockGroupLog blockGroupLog;

    public final NumberPath<Integer> blockLog_seq = createNumber("blockLog_seq", Integer.class);

    public final NumberPath<Integer> soldOutCnt = createNumber("soldOutCnt", Integer.class);

    public QBlockLog(String variable) {
        this(BlockLog.class, forVariable(variable), INITS);
    }

    public QBlockLog(Path<? extends BlockLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlockLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlockLog(PathMetadata metadata, PathInits inits) {
        this(BlockLog.class, metadata, inits);
    }

    public QBlockLog(Class<? extends BlockLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blockGroupLog = inits.isInitialized("blockGroupLog") ? new kr.worthseeing.blockgroup.entity.QBlockGroupLog(forProperty("blockGroupLog"), inits.get("blockGroupLog")) : null;
    }

}

