package kr.worthseeing.main.auction.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuctionLog is a Querydsl query type for AuctionLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuctionLog extends EntityPathBase<AuctionLog> {

    private static final long serialVersionUID = -2068155292L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuctionLog auctionLog = new QAuctionLog("auctionLog");

    public final NumberPath<Integer> auction_seq = createNumber("auction_seq", Integer.class);

    public final NumberPath<Integer> auctionLog_seq = createNumber("auctionLog_seq", Integer.class);

    public final NumberPath<Integer> auctionPrice = createNumber("auctionPrice", Integer.class);

    public final NumberPath<Integer> finishPrice = createNumber("finishPrice", Integer.class);

    public final kr.worthseeing.main.reservation.entity.QReservationLog reservationLog;

    public final NumberPath<Integer> status_seq = createNumber("status_seq", Integer.class);

    public final DateTimePath<java.util.Date> suggestDate = createDateTime("suggestDate", java.util.Date.class);

    public final NumberPath<Integer> suggestPrice = createNumber("suggestPrice", Integer.class);

    public final StringPath userId = createString("userId");

    public QAuctionLog(String variable) {
        this(AuctionLog.class, forVariable(variable), INITS);
    }

    public QAuctionLog(Path<? extends AuctionLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuctionLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuctionLog(PathMetadata metadata, PathInits inits) {
        this(AuctionLog.class, metadata, inits);
    }

    public QAuctionLog(Class<? extends AuctionLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reservationLog = inits.isInitialized("reservationLog") ? new kr.worthseeing.main.reservation.entity.QReservationLog(forProperty("reservationLog"), inits.get("reservationLog")) : null;
    }

}

