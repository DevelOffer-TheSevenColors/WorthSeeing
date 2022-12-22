package kr.worthseeing.main.auction.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuction is a Querydsl query type for Auction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuction extends EntityPathBase<Auction> {

    private static final long serialVersionUID = -1129496896L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuction auction = new QAuction("auction");

    public final NumberPath<Integer> auction_seq = createNumber("auction_seq", Integer.class);

    public final NumberPath<Integer> auctionPrice = createNumber("auctionPrice", Integer.class);

    public final NumberPath<Integer> maxPrice = createNumber("maxPrice", Integer.class);

    public final kr.worthseeing.main.reservation.entity.QReservation reservation;

    public final DateTimePath<java.util.Date> suggestDate = createDateTime("suggestDate", java.util.Date.class);

    public final NumberPath<Integer> suggestPrice = createNumber("suggestPrice", Integer.class);

    public final StringPath userAutoId = createString("userAutoId");

    public final StringPath userId = createString("userId");

    public QAuction(String variable) {
        this(Auction.class, forVariable(variable), INITS);
    }

    public QAuction(Path<? extends Auction> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuction(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuction(PathMetadata metadata, PathInits inits) {
        this(Auction.class, metadata, inits);
    }

    public QAuction(Class<? extends Auction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reservation = inits.isInitialized("reservation") ? new kr.worthseeing.main.reservation.entity.QReservation(forProperty("reservation"), inits.get("reservation")) : null;
    }

}

