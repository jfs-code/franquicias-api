-- TABLE: franchise

CREATE TABLE franchise (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,

    CONSTRAINT pk_franchise
        PRIMARY KEY (id),

    CONSTRAINT uk_franchise_name
        UNIQUE (name)
);

-- TABLE: branch

CREATE TABLE branch (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    franchise_id BIGINT NOT NULL,

    CONSTRAINT pk_branch
        PRIMARY KEY (id),

    CONSTRAINT fk_branch_franchise
        FOREIGN KEY (franchise_id)
        REFERENCES franchise(id)
        ON DELETE CASCADE,

    CONSTRAINT uk_branch_name_franchise
        UNIQUE (name, franchise_id)
);

CREATE INDEX idx_branch_franchise
ON branch(franchise_id);

-- TABLE: product

CREATE TABLE product (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    branch_id BIGINT NOT NULL,

    CONSTRAINT pk_product
        PRIMARY KEY (id),

    CONSTRAINT fk_product_branch
        FOREIGN KEY (branch_id)
        REFERENCES branch(id)
        ON DELETE CASCADE,

    CONSTRAINT uk_product_name_branch
        UNIQUE (name, branch_id),

    CONSTRAINT chk_product_stock
        CHECK (stock >= 0)
);

CREATE INDEX idx_product_branch
ON product(branch_id);

CREATE INDEX idx_product_stock
ON product(stock);