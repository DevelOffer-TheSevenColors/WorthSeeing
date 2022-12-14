package kr.worthseeing.blockgroup.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlockGroup is a Querydsl query type for BlockGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlockGroup extends EntityPathBase<BlockGroup> {

    private static final long serialVersionUID = 1455116377L;

    public static final QBlockGroup blockGroup = new QBlockGroup("blockGroup");

    public final NumberPath<Integer> avgPrice = createNumber("avgPrice", Integer.class);

    public final StringPath cImg = createString("cImg");

    public final NumberPath<Integer> clickCnt = createNumber("clickCnt", Integer.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Integer> groupBlock_seq = createNumber("groupBlock_seq", Integer.class);

    public final DateTimePath<java.util.Date> groupDate = createDateTime("groupDate", java.util.Date.class);

    public final StringPath linkUrl = createString("linkUrl");

    public final StringPath sImg = createString("sImg");

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final StringPath userId = createString("userId");

    public QBlockGroup(String variable) {
        super(BlockGroup.class, forVariable(variable));
    }

    public QBlockGroup(Path<? extends BlockGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlockGroup(PathMetadata metadata) {
        super(BlockGroup.class, metadata);
    }

}

