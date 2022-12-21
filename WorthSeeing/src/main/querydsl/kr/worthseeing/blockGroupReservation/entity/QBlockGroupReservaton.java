package kr.worthseeing.blockGroupReservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlockGroupReservaton is a Querydsl query type for BlockGroupReservaton
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlockGroupReservaton extends EntityPathBase<BlockGroupReservaton> {

    private static final long serialVersionUID = 323781580L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlockGroupReservaton blockGroupReservaton = new QBlockGroupReservaton("blockGroupReservaton");

    public final NumberPath<Integer> avgPrice = createNumber("avgPrice", Integer.class);

    public final kr.worthseeing.blockgroup.entity.QBlockGroup blockGroup;

    public final NumberPath<Integer> blockGroupReservation_seq = createNumber("blockGroupReservation_seq", Integer.class);

    public final ListPath<kr.worthseeing.block.entity.Block, kr.worthseeing.block.entity.QBlock> blockList = this.<kr.worthseeing.block.entity.Block, kr.worthseeing.block.entity.QBlock>createList("blockList", kr.worthseeing.block.entity.Block.class, kr.worthseeing.block.entity.QBlock.class, PathInits.DIRECT2);

    public final StringPath cImg = createString("cImg");

    public final NumberPath<Integer> clickCnt = createNumber("clickCnt", Integer.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> groupDate = createDateTime("groupDate", java.util.Date.class);

    public final StringPath linkUrl = createString("linkUrl");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> purchaseDay = createNumber("purchaseDay", Integer.class);

    public final ListPath<kr.worthseeing.refund.entity.Refund, kr.worthseeing.refund.entity.QRefund> refundList = this.<kr.worthseeing.refund.entity.Refund, kr.worthseeing.refund.entity.QRefund>createList("refundList", kr.worthseeing.refund.entity.Refund.class, kr.worthseeing.refund.entity.QRefund.class, PathInits.DIRECT2);

    public final NumberPath<Integer> reservation = createNumber("reservation", Integer.class);

    public final StringPath sImg = createString("sImg");

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final kr.worthseeing.status.entity.QStatus status;

    public final StringPath users = createString("users");

    public QBlockGroupReservaton(String variable) {
        this(BlockGroupReservaton.class, forVariable(variable), INITS);
    }

    public QBlockGroupReservaton(Path<? extends BlockGroupReservaton> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlockGroupReservaton(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlockGroupReservaton(PathMetadata metadata, PathInits inits) {
        this(BlockGroupReservaton.class, metadata, inits);
    }

    public QBlockGroupReservaton(Class<? extends BlockGroupReservaton> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blockGroup = inits.isInitialized("blockGroup") ? new kr.worthseeing.blockgroup.entity.QBlockGroup(forProperty("blockGroup"), inits.get("blockGroup")) : null;
        this.status = inits.isInitialized("status") ? new kr.worthseeing.status.entity.QStatus(forProperty("status")) : null;
    }

}

