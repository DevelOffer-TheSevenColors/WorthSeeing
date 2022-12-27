package kr.worthseeing.block.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlock is a Querydsl query type for Block
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlock extends EntityPathBase<Block> {

    private static final long serialVersionUID = 74033307L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlock block = new QBlock("block");

    public final NumberPath<Integer> block_seq = createNumber("block_seq", Integer.class);

    public final kr.worthseeing.blockgroup.entity.QBlockGroup blockGroup;

    public final kr.worthseeing.blockGroupWaiting.entity.QBlockGroupWaiting blockGroupWaiting;

    public final NumberPath<Integer> blockPrice = createNumber("blockPrice", Integer.class);

    public final kr.worthseeing.status.entity.QStatus status;

    public final NumberPath<Integer> xLocation = createNumber("xLocation", Integer.class);

    public final NumberPath<Integer> yLocation = createNumber("yLocation", Integer.class);

    public QBlock(String variable) {
        this(Block.class, forVariable(variable), INITS);
    }

    public QBlock(Path<? extends Block> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlock(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlock(PathMetadata metadata, PathInits inits) {
        this(Block.class, metadata, inits);
    }

    public QBlock(Class<? extends Block> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blockGroup = inits.isInitialized("blockGroup") ? new kr.worthseeing.blockgroup.entity.QBlockGroup(forProperty("blockGroup"), inits.get("blockGroup")) : null;
        this.blockGroupWaiting = inits.isInitialized("blockGroupWaiting") ? new kr.worthseeing.blockGroupWaiting.entity.QBlockGroupWaiting(forProperty("blockGroupWaiting"), inits.get("blockGroupWaiting")) : null;
        this.status = inits.isInitialized("status") ? new kr.worthseeing.status.entity.QStatus(forProperty("status")) : null;
    }

}

