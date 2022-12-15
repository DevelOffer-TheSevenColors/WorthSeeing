package kr.worthseeing.blockgroup.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlockGroupLog is a Querydsl query type for BlockGroupLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlockGroupLog extends EntityPathBase<BlockGroupLog> {

    private static final long serialVersionUID = 267145259L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlockGroupLog blockGroupLog = new QBlockGroupLog("blockGroupLog");

    public final NumberPath<Integer> avgPrice = createNumber("avgPrice", Integer.class);

    public final ListPath<kr.worthseeing.block.entity.BlockLog, kr.worthseeing.block.entity.QBlockLog> blockLogList = this.<kr.worthseeing.block.entity.BlockLog, kr.worthseeing.block.entity.QBlockLog>createList("blockLogList", kr.worthseeing.block.entity.BlockLog.class, kr.worthseeing.block.entity.QBlockLog.class, PathInits.DIRECT2);

    public final StringPath cImg = createString("cImg");

    public final NumberPath<Integer> clickCnt = createNumber("clickCnt", Integer.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Integer> groupBlock_seq = createNumber("groupBlock_seq", Integer.class);

    public final NumberPath<Integer> groupBlockLog_seq = createNumber("groupBlockLog_seq", Integer.class);

    public final DateTimePath<java.util.Date> groupDate = createDateTime("groupDate", java.util.Date.class);

    public final StringPath linkUrl = createString("linkUrl");

    public final kr.worthseeing.main.reservation.entity.QReservationLog reservationLog;

    public final StringPath sImg = createString("sImg");

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final StringPath userId = createString("userId");

    public QBlockGroupLog(String variable) {
        this(BlockGroupLog.class, forVariable(variable), INITS);
    }

    public QBlockGroupLog(Path<? extends BlockGroupLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlockGroupLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlockGroupLog(PathMetadata metadata, PathInits inits) {
        this(BlockGroupLog.class, metadata, inits);
    }

    public QBlockGroupLog(Class<? extends BlockGroupLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reservationLog = inits.isInitialized("reservationLog") ? new kr.worthseeing.main.reservation.entity.QReservationLog(forProperty("reservationLog"), inits.get("reservationLog")) : null;
    }

}

