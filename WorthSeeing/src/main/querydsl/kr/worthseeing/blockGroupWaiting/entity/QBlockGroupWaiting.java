package kr.worthseeing.blockGroupWaiting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlockGroupWaiting is a Querydsl query type for BlockGroupWaiting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlockGroupWaiting extends EntityPathBase<BlockGroupWaiting> {

    private static final long serialVersionUID = -1514436773L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlockGroupWaiting blockGroupWaiting = new QBlockGroupWaiting("blockGroupWaiting");

    public final DateTimePath<java.util.Date> auctionDate = createDateTime("auctionDate", java.util.Date.class);

    public final NumberPath<Integer> blockGroupWaiting_seq = createNumber("blockGroupWaiting_seq", Integer.class);

    public final ListPath<kr.worthseeing.block.entity.Block, kr.worthseeing.block.entity.QBlock> blockList = this.<kr.worthseeing.block.entity.Block, kr.worthseeing.block.entity.QBlock>createList("blockList", kr.worthseeing.block.entity.Block.class, kr.worthseeing.block.entity.QBlock.class, PathInits.DIRECT2);

    public final StringPath blockNumber = createString("blockNumber");

    public final StringPath cImg = createString("cImg");

    public final NumberPath<Integer> clickCnt = createNumber("clickCnt", Integer.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> groupDate = createDateTime("groupDate", java.util.Date.class);

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final StringPath linkUrl = createString("linkUrl");

    public final NumberPath<Integer> minBlockSeq = createNumber("minBlockSeq", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DateTimePath<java.util.Date> purchaseDay = createDateTime("purchaseDay", java.util.Date.class);

    public final kr.worthseeing.main.reservation.entity.QReservation reservation;

    public final StringPath sImg = createString("sImg");

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final kr.worthseeing.status.entity.QStatus status;

    public final kr.worthseeing.users.entity.QUsers users;

    public final NumberPath<Integer> width = createNumber("width", Integer.class);

    public QBlockGroupWaiting(String variable) {
        this(BlockGroupWaiting.class, forVariable(variable), INITS);
    }

    public QBlockGroupWaiting(Path<? extends BlockGroupWaiting> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlockGroupWaiting(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlockGroupWaiting(PathMetadata metadata, PathInits inits) {
        this(BlockGroupWaiting.class, metadata, inits);
    }

    public QBlockGroupWaiting(Class<? extends BlockGroupWaiting> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reservation = inits.isInitialized("reservation") ? new kr.worthseeing.main.reservation.entity.QReservation(forProperty("reservation"), inits.get("reservation")) : null;
        this.status = inits.isInitialized("status") ? new kr.worthseeing.status.entity.QStatus(forProperty("status")) : null;
        this.users = inits.isInitialized("users") ? new kr.worthseeing.users.entity.QUsers(forProperty("users")) : null;
    }

}

