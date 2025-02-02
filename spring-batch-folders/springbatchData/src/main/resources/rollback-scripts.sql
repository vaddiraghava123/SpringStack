commit;

drop table batch_job_seq;
drop table batch_job_execution_seq;
drop table batch_step_execution_seq;

drop table batch_job_instance;
drop table batch_job_execution_params;
drop table batch_job_execution;

drop table batch_step_execution;
drop table batch_job_execution_context;

drop table batch_step_execution_context;

--Product table
CREATE TABLE PRODUCT(
	prodId BIGINT NOT NULL PRIMARY KEY,
    prodCode VARCHAR(2500) NOT NULL,
    prodCost BIGINT,
    prodDisc BIGINT,
    prodGst BIGINT
);
