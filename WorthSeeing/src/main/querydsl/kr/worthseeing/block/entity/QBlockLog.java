package kr.worthseeing.block.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlockLog is a Querydsl query type for BlockLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlockLog extends EntityPathBase<BlockLog> {

    private static final long serialVersionUID = -2086864727L;

    public static final QBlockLog blockLog = new QBlockLog("blockLog");

    public final NumberPath<Integer> block_seq = createNumber("block_seq", Integer.class);

    public final NumberPath<Integer> blockLog_seq = createNumber("blockLog_seq", Integer.class);

    public final NumberPath<Integer> groupBlock_seq = createNumber("groupBlock_seq", Integer.class);

    public final NumberPath<Integer> soldOutCnt = createNumber("soldOutCnt", Integer.class);

    public QBlockLog(String variable) {
        super(BlockLog.class, forVariable(variable));
    }

    public QBlockLog(Path<? extends BlockLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlockLog(PathMetadata metadata) {
        super(BlockLog.class, metadata);
    }

}

