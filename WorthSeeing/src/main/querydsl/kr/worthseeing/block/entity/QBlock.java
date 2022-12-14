package kr.worthseeing.block.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlock is a Querydsl query type for Block
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlock extends EntityPathBase<Block> {

    private static final long serialVersionUID = 74033307L;

    public static final QBlock block = new QBlock("block");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Integer> groupBlock_seq = createNumber("groupBlock_seq", Integer.class);

    public final NumberPath<Integer> linkBoard_seq = createNumber("linkBoard_seq", Integer.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QBlock(String variable) {
        super(Block.class, forVariable(variable));
    }

    public QBlock(Path<? extends Block> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlock(PathMetadata metadata) {
        super(Block.class, metadata);
    }

}

