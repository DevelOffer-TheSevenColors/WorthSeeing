package kr.worthseeing.blockgroup.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlockGroup is a Querydsl query type for BlockGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlockGroup extends EntityPathBase<BlockGroup> {

    private static final long serialVersionUID = 1455116377L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlockGroup blockGroup = new QBlockGroup("blockGroup");

    public final NumberPath<Integer> avgPrice = createNumber("avgPrice", Integer.class);

    public final NumberPath<Integer> blockGroup_seq = createNumber("blockGroup_seq", Integer.class);

    public final ListPath<kr.worthseeing.block.entity.Block, kr.worthseeing.block.entity.QBlock> blockList = this.<kr.worthseeing.block.entity.Block, kr.worthseeing.block.entity.QBlock>createList("blockList", kr.worthseeing.block.entity.Block.class, kr.worthseeing.block.entity.QBlock.class, PathInits.DIRECT2);

    public final StringPath cImg = createString("cImg");

    public final NumberPath<Integer> clickCnt = createNumber("clickCnt", Integer.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> groupDate = createDateTime("groupDate", java.util.Date.class);

    public final StringPath linkUrl = createString("linkUrl");

    public final ListPath<kr.worthseeing.notify.entity.Notify, kr.worthseeing.notify.entity.QNotify> notifyList = this.<kr.worthseeing.notify.entity.Notify, kr.worthseeing.notify.entity.QNotify>createList("notifyList", kr.worthseeing.notify.entity.Notify.class, kr.worthseeing.notify.entity.QNotify.class, PathInits.DIRECT2);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final ListPath<kr.worthseeing.refund.entity.Refund, kr.worthseeing.refund.entity.QRefund> refundList = this.<kr.worthseeing.refund.entity.Refund, kr.worthseeing.refund.entity.QRefund>createList("refundList", kr.worthseeing.refund.entity.Refund.class, kr.worthseeing.refund.entity.QRefund.class, PathInits.DIRECT2);

    public final ListPath<kr.worthseeing.main.reservation.entity.Reservation, kr.worthseeing.main.reservation.entity.QReservation> reservationList = this.<kr.worthseeing.main.reservation.entity.Reservation, kr.worthseeing.main.reservation.entity.QReservation>createList("reservationList", kr.worthseeing.main.reservation.entity.Reservation.class, kr.worthseeing.main.reservation.entity.QReservation.class, PathInits.DIRECT2);

    public final StringPath sImg = createString("sImg");

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final kr.worthseeing.users.entity.QUsers users;

    public QBlockGroup(String variable) {
        this(BlockGroup.class, forVariable(variable), INITS);
    }

    public QBlockGroup(Path<? extends BlockGroup> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlockGroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlockGroup(PathMetadata metadata, PathInits inits) {
        this(BlockGroup.class, metadata, inits);
    }

    public QBlockGroup(Class<? extends BlockGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.users = inits.isInitialized("users") ? new kr.worthseeing.users.entity.QUsers(forProperty("users"), inits.get("users")) : null;
    }

}

