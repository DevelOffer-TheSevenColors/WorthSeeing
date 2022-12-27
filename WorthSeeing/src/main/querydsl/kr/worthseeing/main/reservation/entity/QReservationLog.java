package kr.worthseeing.main.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservationLog is a Querydsl query type for ReservationLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservationLog extends EntityPathBase<ReservationLog> {

    private static final long serialVersionUID = -1758077948L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservationLog reservationLog = new QReservationLog("reservationLog");

    public final kr.worthseeing.main.auction.entity.QAuctionLog auctionLog;

    public final kr.worthseeing.blockGroupWaiting.entity.QBlockGroupWaitingLog blockGroupWaitingLog;

    public final NumberPath<Integer> reservation_seq = createNumber("reservation_seq", Integer.class);

    public final NumberPath<Integer> reservationLog_seq = createNumber("reservationLog_seq", Integer.class);

    public final DateTimePath<java.util.Date> reservationTime = createDateTime("reservationTime", java.util.Date.class);

    public final NumberPath<Integer> startPrice = createNumber("startPrice", Integer.class);

    public final StringPath useId = createString("useId");

    public QReservationLog(String variable) {
        this(ReservationLog.class, forVariable(variable), INITS);
    }

    public QReservationLog(Path<? extends ReservationLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservationLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservationLog(PathMetadata metadata, PathInits inits) {
        this(ReservationLog.class, metadata, inits);
    }

    public QReservationLog(Class<? extends ReservationLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auctionLog = inits.isInitialized("auctionLog") ? new kr.worthseeing.main.auction.entity.QAuctionLog(forProperty("auctionLog"), inits.get("auctionLog")) : null;
        this.blockGroupWaitingLog = inits.isInitialized("blockGroupWaitingLog") ? new kr.worthseeing.blockGroupWaiting.entity.QBlockGroupWaitingLog(forProperty("blockGroupWaitingLog"), inits.get("blockGroupWaitingLog")) : null;
    }

}

