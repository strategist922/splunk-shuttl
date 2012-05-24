// Copyright (C) 2011 Splunk Inc.
//
// Splunk Inc. licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.splunk.shuttl.archiver.thaw;

import com.splunk.shuttl.archiver.archive.BucketFormat;
import com.splunk.shuttl.archiver.model.Bucket;

/**
 * Restores a {@link Bucket} that's in any {@link BucketFormat} to
 * {@link BucketFormat#SPLUNK_BUCKET}.
 */
public class BucketRestorer {

    /**
     * @param bucket
     *            to restore to {@link BucketFormat#SPLUNK_BUCKET}.
     * @return
     */
    public Bucket restoreToSplunkBucketFormat(Bucket bucket) {
	return bucket;
    }

}